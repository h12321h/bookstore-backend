package org.example.mainservice.dao;

import org.example.mainservice.entity.Cart;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartDao {
    List<Cart> findCartByUserId(int id);
    Cart findCartById(int id);
    void saveCart(Cart cart);
    void deleteById(int id);

    List<Cart> findCartByUserIdAndPage(int userId, Pageable pageable);
}