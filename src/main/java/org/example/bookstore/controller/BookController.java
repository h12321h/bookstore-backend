package org.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.BookService;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.findBookById(id);
    }

//    @GetMapping("/search")
//    public List<Book> searchBook(@RequestParam("type") String type,@RequestParam("query") String query){
//        List<Book> result = new ArrayList<>();
//        for (Book book : books) {
//            if (type.equals("title") && book.getTitle().toLowerCase().contains(query.toLowerCase())) {
//                result.add(book);
//            } else if (type.equals("author") && book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
//                result.add(book);
//            } else if (type.equals("publisher") && book.getPublisher().toLowerCase().contains(query.toLowerCase())) {
//                result.add(book);
//            }
//        }
//        return result;
//    }
}
