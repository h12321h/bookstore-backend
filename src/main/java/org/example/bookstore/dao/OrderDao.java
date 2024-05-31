package org.example.bookstore.dao;

import org.example.bookstore.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    List<Order> findByUserId(int userId);
    void saveOrder(Order order);
    void deleteById(int id);

    List<Order> findByUserIdAndBookName(int userId, String bookName);

    List<Order> findByUserIdAndDate(int userId, Date startDate, Date endDate);

    List<Order> findByUserIdAndBookNameAndDate(int userId, String bookName, Date startDate, Date endDate);
}
