package org.example.mainservice.daoimpl;

import org.example.mainservice.dao.BookDao;
import org.example.mainservice.entity.Book;
import org.example.mainservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Book> findAllBooks(Pageable pageable) {
        Specification<Book> spec = (root, query, cb) -> cb.equal(root.get("deleted"), false);
        Page<Book> page = bookRepository.findAll(spec, pageable);
        return page;
    }

    @Override
    public Page<Book> findByTitle(String title,Pageable pageable) {
        return bookRepository.findByTitle(title,pageable);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public List<Book> findByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Boolean deleteBook(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setDeleted(true);
            book.setStock(0);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Boolean decreaseStock(int id, int quantity) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return false;
        }
        int stock = book.getStock();
        if (stock < quantity) {
            return false;
        }
        book.setStock(stock - quantity);
        bookRepository.save(book);
        return true;
    }

    @Override
    public Boolean checkStock(int id, int quantity) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return false;
        }
        if(quantity<=0){
            return false;
        }
        int stock = book.getStock();
        return stock >= quantity;
    }

    @Override
    public Integer getBooksNum() {
        return bookRepository.getBooksNum();
    }

    @Override
    public Integer getNumByTitle(String title) {
        return bookRepository.getNumByTitle(title);
    }
}