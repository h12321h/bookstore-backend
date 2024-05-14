package org.example.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.BookService;
import org.example.bookstore.utils.SessionUtils;

import javax.net.ssl.HandshakeCompletedEvent;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public List<Book> getBooks() {
        HttpSession session = SessionUtils.getSession();
        if (session != null ) {
            System.out.println("book Session ID: " + session.getId());
        }
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/search/{type}/{query}")
    public List<Book> searchBook(@PathVariable String type,@PathVariable String query){
        List<Book> result = bookService.searchBook(type,query);
        return result;
    }
}
