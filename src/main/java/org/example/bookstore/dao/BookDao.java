package org.example.bookstore.dao;

import org.example.bookstore.entity.Book;

import java.util.List;


public interface BookDao {
    Book findBookById(int id);
    List<Book> findAllBooks();

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);
}
