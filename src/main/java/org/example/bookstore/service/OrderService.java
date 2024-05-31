package org.example.bookstore.service;

import org.example.bookstore.dto.OrderDto;

import org.example.bookstore.dto.BuyItem;

import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderDto> findByUserId(int userId);
    Boolean saveOrder(List<BuyItem> buyItemList, Integer userId, String name, String address, String phone);
    void deleteOrder(Integer id);

    List<OrderDto> findByUserIdAndBookName(Integer userId, String bookName);
    List<OrderDto> findByUserIdAndDate(Integer userId, Date startDate, Date endDate);
    List<OrderDto> findByUserIdAndBookNameAndDate(Integer userId, String bookName, Date startDate, Date endDate);

}
