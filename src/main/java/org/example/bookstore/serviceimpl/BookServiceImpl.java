package org.example.bookstore.serviceimpl;

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
    public List<Book> findAll() {
        return bookDao.findAllBooks();
    }

    @Override
    public List<Book> searchBook(String type, String query) {
        if (type.equals("title")) {
            return bookDao.findByTitle(query);
        } else if (type.equals("author")) {
            return bookDao.findByAuthor(query);
        } else if (type.equals("publisher")) {
            return bookDao.findByPublisher(query);
        } else {
            return null;
        }
    }
}
