package org.example.bookstore.daoimpl;


import org.example.bookstore.entity.Cart;
import org.example.bookstore.dao.CartDao;
import org.example.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findCartByUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart findCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
