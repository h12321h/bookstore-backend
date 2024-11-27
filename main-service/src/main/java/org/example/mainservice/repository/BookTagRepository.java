package org.example.mainservice.repository;

import org.example.mainservice.entity.Book;
import org.example.mainservice.entity.BookTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookTagRepository extends JpaRepository<BookTag, Integer> {
    BookTag findBookTagById(Integer id);

    // 根据标签ID查找关联的书籍
    @Query("select b.bookId from BookTag b where b.tagId = :tagId")
    List<Integer> findBookIdByTagId(@Param("tagId") Integer tagId);
}
