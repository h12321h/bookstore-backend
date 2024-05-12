package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.BookDao;
import org.example.bookstore.entity.Book;
import org.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }
}