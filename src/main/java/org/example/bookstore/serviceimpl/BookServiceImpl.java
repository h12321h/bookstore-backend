package org.example.bookstore.serviceimpl;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.example.bookstore.dao.BookDao;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String BOOK_CACHE_KEY_PREFIX = "book:";
    private static final String BOOK_CACHE_KEY_NUM = "book:num";
    private static final String BOOK_IDS_KEY = "books"; // 存储所有书籍id的Sorted Set

   // @PostConstruct
    public void cacheAllBooks() {
        try{
            List<Book> books = bookDao.findAllBooks(Pageable.unpaged()).getContent();
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();

            for (Book book : books) {
                int id = book.getId();
                String cacheKey = getBookCacheKey(id);

                // 缓存每本书的详细信息
                ops.setIfAbsent(cacheKey, book, 10, TimeUnit.MINUTES);

                // 将书籍ID存入Sorted Set中，用ID作为score实现自然排序
                zSetOps.add(BOOK_IDS_KEY, id, id);

                System.out.println("Cached book: " + id);
            }

            ops.setIfAbsent(BOOK_CACHE_KEY_NUM, books.size(), 10, TimeUnit.MINUTES);
        } catch (Exception e) {
            System.out.println("Redis is not available. Caching all books to Redis failed.");
        }

    }

    private String getBookCacheKey(int id) {
        return BOOK_CACHE_KEY_PREFIX + id;
    }

    @Override
    public Book findBookById(int id) {
        try{
            String cacheKey = getBookCacheKey(id);
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();

            // 从缓存中获取书籍
            Book book = (Book) ops.get(cacheKey);
            if (book != null) {
                System.out.println("Retrieved book from cache: " + id);
                return book;
            }

            // 缓存中没有则从数据库获取并缓存
            book = bookDao.findBookById(id);
            if (book != null) {
                ops.set(cacheKey, book, 10, TimeUnit.MINUTES); // 设置缓存有效期为10分钟
                System.out.println("Caching book: " + id);
            }

            return bookDao.findBookById(id);
        }catch (Exception e){
            System.out.println("Redis is not available. Reading from database.");
            return bookDao.findBookById(id);
        }

    }

    @Override
    public List<Book> findAll(Pageable pageable) {
//        Page<Book> books = bookDao.findAllBooks(pageable);
//        return books.getContent();
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int start = pageNumber * pageSize;
        int end = start + pageSize - 1;

        List<Book> books = new ArrayList<>();

        try {
            ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            // 检查 Redis 中是否已缓存书籍 ID，如果没有则缓存所有书籍
            if (zSetOps.size(BOOK_IDS_KEY) == 0) {
                cacheAllBooks();
            }

            // 从 Redis 的 Sorted Set 中获取分页 ID 列表
            Set<Object> bookIds = zSetOps.range(BOOK_IDS_KEY, start, end);

            for (Object idObj : bookIds) {
                int id = (int) idObj;
                String cacheKey = getBookCacheKey(id);

                // 从缓存中获取每本书的详细信息
                Book book = (Book) ops.get(cacheKey);
                if (book == null) {
                    // 如果缓存中没有，则从数据库获取并更新缓存
                    book = bookDao.findBookById(id);
                    if (book != null) {
                        ops.set(cacheKey, book, 10, TimeUnit.MINUTES);
                        System.out.println("Caching book: " + id);
                    }
                }
                if (book != null) {
                    books.add(book);
                    System.out.println("Retrieved book from cache: " + id);
                }
            }
        } catch (Exception e) {
            System.out.println("Redis is not available. Reading from database.");
            // 当 Redis 不可用时，直接从数据库中读取分页数据
            Page<Book> dbBooks = bookDao.findAllBooks(pageable);
            books = dbBooks.getContent();
        }
        return books;
    }

    @Override
    public List<Book> searchBook(String type, String query,Pageable pageable) {
        List<Book> books = new ArrayList<>();
        try{
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();

            // 从Sorted Set中获取所有书籍ID
            Set<Object> bookIds = zSetOps.range(BOOK_IDS_KEY, 0, -1);

            // 从缓存中取出所有书籍，并根据条件进行过滤
            for (Object idObj : bookIds) {
                int id = (int) idObj;
                String cacheKey = getBookCacheKey(id);
                Book book = (Book) ops.get(cacheKey);

                // 如果缓存中没有该书籍数据，跳过此书
                if (book == null) {
                    continue;
                }

                // 根据查询类型过滤
                boolean matches = false;
                switch (type) {
                    case "title":
                        matches = book.getTitle().contains(query);
                        break;
                    case "author":
                        matches = book.getAuthor().contains(query);
                        break;
                    case "publisher":
                        matches = book.getPublisher().contains(query);
                        break;
                    default:
                        return null;
                }

                // 如果匹配，则加入结果集
                if (matches) {
                    books.add(book);
                }
            }

            // 处理分页
            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), books.size());
            if (start > end) {
                return new ArrayList<>(); // 若起始索引超过总结果数，返回空列表
            }

            return books.subList(start, end);
        } catch (Exception e) {
            System.out.println("Redis is not available. Reading from database.");
            switch (type) {
                case "title":
                    books = bookDao.findByTitle(query, pageable).getContent();
                    break;
                case "author":
                    books = bookDao.findByAuthor(query);
                    break;
                case "publisher":
                    books = bookDao.findByPublisher(query);
                    break;
                default:
                    return null;
            }

            return books;
        }
    }

    @Override
    public Integer getBooksNum() {
        try{
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();
            Integer num = (Integer) ops.get(BOOK_CACHE_KEY_NUM);
            if (num != null) {
                System.out.println("Retrieved book number from cache.");
                return num;
            }

            num = bookDao.getBooksNum();
            ops.set(BOOK_CACHE_KEY_NUM, num, 10, TimeUnit.MINUTES);
            System.out.println("Caching book number.");
            return num;
        } catch (Exception e) {
            System.out.println("Redis is not available. Reading from database.");
            return bookDao.getBooksNum();
        }
    }

    @Override
    public Integer getNumByTitle(String title) {
        return bookDao.getNumByTitle(title);
    }

    @Override
    public Boolean deleteBook(int id) {
        boolean isDeleted = bookDao.deleteBook(id);
        // 从缓存中删除书籍
        if (isDeleted) {
            try{
                String cacheKey = getBookCacheKey(id);
                redisTemplate.delete(cacheKey);
                redisTemplate.opsForZSet().remove(BOOK_IDS_KEY, id); // 从Sorted Set中移除该ID
                System.out.println("Deleted book from cache and Sorted Set: " + id);
            }catch (Exception e){
                System.out.println("Redis is not available. Deleting from database only.");
            }
        }
        return isDeleted;
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
        try{// 更新缓存
            String cacheKey = getBookCacheKey(book.getId());
            redisTemplate.opsForValue().set(cacheKey, book, 10, TimeUnit.MINUTES);
            // 将书籍ID添加到Sorted Set中
            redisTemplate.opsForZSet().add(BOOK_IDS_KEY, book.getId(), book.getId()); // 使用ID作为score排序
            System.out.println("Saved book to cache and added to Sorted Set: " + book.getId());
        } catch (Exception e) {
            System.out.println("Redis is not available. Saving to database only.");
        }
    }
}
