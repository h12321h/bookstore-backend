package org.example.bookstore.dao;

import org.example.bookstore.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    List<OrderItem> findByOrderId(int orderId);
    void saveOrderItem(OrderItem orderItem);
}