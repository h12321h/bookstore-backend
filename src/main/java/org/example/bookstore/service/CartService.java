package org.example.bookstore.service;

import org.example.bookstore.entity.Cart;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    List<Cart> findCartByUserId(int user_id, Pageable pageable);
    String addBookToCart(int user_id, int book_id);
    void deleteBookFromCart(int id);
    void updateBookQuantity(int id,int quantity);

    int findCartNumByUserId(Integer userId);
}
