package org.example.mainservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import org.example.mainservice.entity.Book;
import org.example.mainservice.service.BookService;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookService.findAll(pageable);
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/search/{type}/{query}")
    public List<Book> searchBook(@PathVariable String type,@PathVariable String query,@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Book> result = bookService.searchBook(type,query,pageable);
        return result;
    }

    @GetMapping("/books/num")
    public Integer getBooksNum() {
        return bookService.getBooksNum();
    }

    @GetMapping("/num/{title}")
    public Integer getNumByTitle(@PathVariable String title) {
        return bookService.getNumByTitle(title);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable int id) {
        if(bookService.deleteBook(id))
            return "success";
        else
            return "fail";
    }

    @PostMapping("/book/save")
    public String saveBook(@RequestBody Book book) {
        book.setDeleted(false);
        bookService.saveBook(book);
        return "success";
    }

    @QueryMapping
    public List<Book> searchBookByTitle(@Argument String title, @Argument int page, @Argument int size) {
        System.out.println("searchBookByTitle");
        System.out.println(title);
        System.out.println(page);
        System.out.println(size);
        Pageable pageable = PageRequest.of(page, size);
        List<Book> result = bookService.searchBook("title",title,pageable);
        System.out.println(result);
        return result;
    }
}
