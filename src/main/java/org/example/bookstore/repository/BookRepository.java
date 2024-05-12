package org.example.bookstore.repository;

import org.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitle(String title);
}
