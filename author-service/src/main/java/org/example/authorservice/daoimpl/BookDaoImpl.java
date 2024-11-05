package org.example.authorservice.dao.impl;

import org.example.authorservice.dao.BookDao;
import org.example.authorservice.entity.Book;
import org.example.authorservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    @Autowired
    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}
