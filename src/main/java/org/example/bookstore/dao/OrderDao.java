package org.example.bookstore.dao;

import org.example.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findByUserId(int userId);
    void saveOrder(Order order);
    void deleteById(int id);
}
