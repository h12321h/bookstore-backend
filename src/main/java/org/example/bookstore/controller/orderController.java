package org.example.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class orderController {
    //硬编码数据
    private static class Book{
        int id;
        String title;
        String author;
        int price;
        int quantity;
        String coverImage;

        public Book(int id, String title, String author,  int price,int quantity,String coverImage) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.price = price;
            this.quantity = quantity;
            this.coverImage = coverImage;
        }
        public int getId() {
            return id;
        }
        public String getTitle() {
            return title;
        }
        public String getAuthor() {
            return author;
        }
        public int getPrice() {
            return price;
        }
        public int getQuantity() {
            return quantity;
        }
        public String getCoverImage() {
            return coverImage;
        }

    }
    private static List<orderController.Book> books = new ArrayList<>();
    static {
        books.add(new orderController.Book(1, "The Great Gatsby", "F. Scott Fitzgerald",  180,1,"images/pc.png"));
        books.add(new orderController.Book(2, "To Kill a Mockingbird", "Harper Lee", 200,1,"images/pc.png"));
        books.add(new orderController.Book(3, "1984", "George Orwell", 220,4,"images/pc.png"));
 }
    @GetMapping("/orders")
    public List<orderController.Book> getBooks() {
        return books;
    }

    public static class buyBook{
        int id;
        int quantity;
        public int getId() {
            return id;
        }
        public int getQuantity() {
            return quantity;
        }
        public buyBook(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
        }
    }
    @PostMapping("/purchase")
    public String handlePurchase(@RequestBody buyBook[] buyBooks) {
        // 处理购物车数据
        for (buyBook item : buyBooks) {
            System.out.println("ID: " + item.getId() + ", Quantity: " + item.getQuantity());
        }

        return "订单确认";
    }

}
