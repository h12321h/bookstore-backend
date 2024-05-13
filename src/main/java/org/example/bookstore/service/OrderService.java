package org.example.bookstore.service;

import org.example.bookstore.dto.OrderDto;
import org.example.bookstore.entity.Order;
import org.example.bookstore.entity.OrderItem;

import org.example.bookstore.dto.BuyItem;

import java.util.List;

public interface OrderService {
    List<OrderDto> findByUserId(int userId);
    void saveOrder(List<BuyItem> buyItemList, Integer userId);
    void deleteOrder(Integer id);
}
