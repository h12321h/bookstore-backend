package org.example.authorservice.dao;

import org.example.authorservice.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findByTitle(String title);
}
