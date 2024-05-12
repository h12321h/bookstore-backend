package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.OrderDao;
import org.example.bookstore.entity.Order;
import org.example.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}