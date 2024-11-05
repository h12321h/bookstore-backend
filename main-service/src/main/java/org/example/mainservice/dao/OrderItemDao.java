package org.example.mainservice.dao;

import org.example.mainservice.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    List<OrderItem> findByOrderId(int orderId);
    void saveOrderItem(OrderItem orderItem);
}