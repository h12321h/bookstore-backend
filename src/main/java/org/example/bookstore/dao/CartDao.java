package org.example.bookstore.dao;

import org.example.bookstore.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartDao {
    List<Cart> findCartByUserId(int id);
    Cart findCartById(int id);
    void saveCart(Cart cart);
    void deleteById(int id);
}