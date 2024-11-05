package org.example.mainservice.repository;

import org.example.mainservice.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    @Query("SELECT b FROM Book b WHERE b.title = :title AND b.deleted = false")
    Page<Book> findByTitle(String title, Pageable pageable);
    List<Book> findByAuthor(String author);
    List<Book> findByPublisher(String publisher);

    //获取书籍数量
    @Query("SELECT COUNT(b) FROM Book b WHERE b.deleted = false")
    Integer getBooksNum();


    @Query("SELECT COUNT(b) FROM Book b WHERE b.title = :title AND b.deleted = false")
    Integer getNumByTitle(String title);
}
