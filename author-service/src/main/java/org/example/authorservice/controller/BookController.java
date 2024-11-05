package org.example.authorservice.controller;

import org.example.authorservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAuthorsByTitle")
    public List<String> getAuthorsByTitle(@RequestParam String title) {
        return bookService.getAuthorsByTitle(title);
    }
}
