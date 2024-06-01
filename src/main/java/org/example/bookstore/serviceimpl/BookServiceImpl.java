package org.example.bookstore.serviceimpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.example.bookstore.dao.BookDao;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(int id) {
        return bookDao.findBookById(id);
    }

    @Override
    public List<Book> findAll(Pageable pageable) {
        Page<Book> books= bookDao.findAllBooks(pageable);
        return books.getContent();
    }

    @Override
    public List<Book> searchBook(String type, String query,Pageable pageable) {
        if (type.equals("title")) {
            return bookDao.findByTitle(query,pageable).getContent();
        } else if (type.equals("author")) {
            return bookDao.findByAuthor(query);
        } else if (type.equals("publisher")) {
            return bookDao.findByPublisher(query);
        } else {
            return null;
        }
    }

    @Override
    public Integer getBooksNum() {
        return bookDao.getBooksNum();
    }

    @Override
    public Integer getNumByTitle(String title) {
        return bookDao.getNumByTitle(title);
    }

    @Override
    public Boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }
}
