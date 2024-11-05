package org.example.mainservice.daoimpl;

import org.example.mainservice.dao.OrderDao;
import org.example.mainservice.entity.Order;
//import org.example.bookstore.repository.OrderItemRepository;
import org.example.mainservice.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Page<Order> findByUserId(int userId, Pageable pageable) {
        return orderRepository.findByUserId(userId, pageable);
    }

    @Override
    @Transactional//(isolation = Isolation.READ_UNCOMMITTED)
    public void saveOrder(Order order) {
        //int result=10/0;
        orderRepository.save(order);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Order> findByUserIdAndBookName(int userId, String bookName, Pageable pageable) {
        return orderRepository.findByUserIdAndBookName(userId, bookName, pageable);
    }

    @Override
    public Page<Order> findByUserIdAndDate(int userId, Date startDate, Date endDate, Pageable pageable) {
        return orderRepository.findByUserIdAndDate(userId, startDate, endDate, pageable);
    }

    @Override
    public Page<Order> findByUserIdAndBookNameAndDate(int userId, String bookName, Date startDate, Date endDate, Pageable pageable) {
        return orderRepository.findByUserIdAndBookNameAndDate(userId, bookName, startDate, endDate, pageable);
    }

    @Override
    public List<Object[]> getBookStatisticByUserId(int userId, Date startDate, Date endDate) {
        return orderRepository.getBookStatisticByUserId(userId, startDate, endDate);
    }

    @Override
    public Integer getOrdersNumByUserIdAndDate(Integer userId, Date startDate, Date endDate) {
        return orderRepository.getOrdersNumByUserIdAndDate(userId,startDate, endDate);
    }

    @Override
    public Integer getOrdersNumByUserIdAndDateAndBookName(Integer userId, Date startDate, Date endDate, String bookName) {
        return orderRepository.getOrdersNumByUserIdAndDateAndBookName(userId,startDate, endDate, bookName);
    }


    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findByBookName(Pageable pageable, String bookName) {
        return orderRepository.findByBookName(pageable, bookName);
    }

    @Override
    public Page<Order> findByDate(Pageable pageable, Date startDate, Date endDate) {
        return orderRepository.findByDate(pageable, startDate, endDate);
    }

    @Override
    public Page<Order> findByBookNameAndDate(Pageable pageable, String bookName, Date startDate, Date endDate) {
        return orderRepository.findByBookNameAndDate(pageable, bookName, startDate, endDate);
    }

    @Override
    public Integer getOrdersNum() {
        return orderRepository.findAll().size();
    }

    @Override
    public Integer getOrdersNumByBookName(String bookName) {
        return orderRepository.findNumByBookName(bookName);
    }

    @Override
    public Integer getOrdersNumByDate(Date startDate, Date endDate) {
        return orderRepository.findNumByDate(startDate, endDate);
    }

    @Override
    public Integer getOrdersNumByBookNameAndDate(String bookName, Date startDate, Date endDate) {
        return orderRepository.findNumByBookNameAndDate(bookName, startDate, endDate);
    }

    @Override
    public List<Object[]> getRangeBook(Pageable pageable, Date startDate, Date endDate) {
        return orderRepository.getRangeBook(pageable, startDate, endDate).getContent();
    }

    @Override
    public Integer getRangeBookNum(Date startDate, Date endDate) {
        return orderRepository.getRangeBookNum(startDate, endDate);
    }

    @Override
    public List<Object[]> getRangeUser(Pageable pageable, Date startDate, Date endDate) {
        return orderRepository.getRangeUser(pageable, startDate, endDate).getContent();
    }

    @Override
    public Integer getRangeUserNum(Date startDate, Date endDate) {
        return orderRepository.getRangeUserNum(startDate, endDate);
    }

    @Override
    public Integer getBookStatisticNum(Integer userId, Date startDate, Date endDate) {
        return orderRepository.getBookStatisticNum(userId, startDate, endDate);
    }

    @Override
    public BigDecimal getBookStatisticPrice(Integer userId, Date startDate, Date endDate) {
        return orderRepository.getBookStatisticPrice(userId, startDate, endDate);
    }

}