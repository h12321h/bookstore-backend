package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.OrderDao;
import org.example.bookstore.entity.Order;
import org.example.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> findByUserIdAndBookName(int userId, String bookName) {
        return orderRepository.findByUserIdAndBookName(userId, bookName);
    }

    @Override
    public List<Order> findByUserIdAndDate(int userId, Date startDate, Date endDate) {
        return orderRepository.findByUserIdAndDate(userId, startDate, endDate);
    }

    @Override
    public List<Order> findByUserIdAndBookNameAndDate(int userId, String bookName, Date startDate, Date endDate) {
        return orderRepository.findByUserIdAndBookNameAndDate(userId, bookName, startDate, endDate);
    }
}