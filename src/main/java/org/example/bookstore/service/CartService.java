package org.example.bookstore.service;

import org.example.bookstore.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findCartByUserId(int user_id);
    boolean addBookToCart(int user_id,int book_id);
    void deleteBookFromCart(int id);
    void updateBookQuantity(int id,int quantity);
}
