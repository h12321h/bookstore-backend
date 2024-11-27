package org.example.mainservice.repository;

import org.example.mainservice.entity.BookCover;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface BookCoverRepository extends MongoRepository<BookCover, Integer> {
    BookCover findBookCoverById(Integer id);
}