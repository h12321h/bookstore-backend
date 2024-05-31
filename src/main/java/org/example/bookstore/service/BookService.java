package org.example.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.example.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> findAll(Pageable pageable);

    List<Book> searchBook(String type, String query);

    Integer getBooksNum();
}
