package org.example.authorservice.service.impl;

import org.example.authorservice.dao.BookDao;
import org.example.authorservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<String> getAuthorsByTitle(String title) {
        var books = bookDao.findByTitle(title);
        return books.stream().map(book -> book.getAuthor()).distinct().collect(Collectors.toList());
    }
}
