package org.example.bookstore.service;

import org.example.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> findAll();
}
