package org.example.bookstore.dao;

import org.example.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BookDao {
    Book findBookById(int id);
    Page<Book> findAllBooks(Pageable pageable);

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);

    void saveBook(Book book);

    void deleteBook(int id);

    Boolean decreaseStock(int id, int quantity);

    Integer getBooksNum();
}
