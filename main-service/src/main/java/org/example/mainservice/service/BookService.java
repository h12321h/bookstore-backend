package org.example.mainservice.service;

import org.springframework.data.domain.Pageable;
import org.example.mainservice.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> findAll(Pageable pageable);

    List<Book> searchBook(String type, String query, Pageable pageable);

    Integer getBooksNum();

    Integer getNumByTitle(String title);

    Boolean deleteBook(int id);

    void saveBook(Book book);
}
