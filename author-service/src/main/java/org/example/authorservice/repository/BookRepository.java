package org.example.authorservice.repository;


import org.example.authorservice.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitle(String title);
}
