package org.example.mainservice.dao;

import org.example.mainservice.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderDao {
    Page<Order> findByUserId(int userId, Pageable pageable);
    void saveOrder(Order order);
    void deleteById(int id);

    Page<Order> findByUserIdAndBookName(int userId, String bookName, Pageable pageable);

    Page<Order> findByUserIdAndDate(int userId, Date startDate, Date endDate, Pageable pageable);

    Page<Order> findByUserIdAndBookNameAndDate(int userId, String bookName, Date startDate, Date endDate, Pageable pageable);

    List<Object[]> getBookStatisticByUserId(int userId, Date startDate, Date endDate);

    Page<Order> findAll(Pageable pageable);

    Page<Order> findByBookName(Pageable pageable, String bookName);

    Page<Order> findByDate(Pageable pageable, Date startDate, Date endDate);

    Page<Order> findByBookNameAndDate(Pageable pageable, String bookName, Date startDate, Date endDate);

    Integer getOrdersNum();

    Integer getOrdersNumByBookName(String bookName);

    Integer getOrdersNumByDate(Date startDate, Date endDate);

    Integer getOrdersNumByBookNameAndDate(String bookName, Date startDate, Date endDate);

    List<Object[]> getRangeBook(Pageable pageable, Date startDate, Date endDate);

    Integer getRangeBookNum(Date startDate, Date endDate);

    List<Object[]> getRangeUser(Pageable pageable, Date startDate, Date endDate);

    Integer getRangeUserNum(Date startDate, Date endDate);

    Integer getBookStatisticNum(Integer userId, Date startDate, Date endDate);

    BigDecimal getBookStatisticPrice(Integer userId, Date startDate, Date endDate);

    Integer getOrdersNumByUserIdAndDate(Integer userId, Date startDate, Date endDate);

    Integer getOrdersNumByUserIdAndDateAndBookName(Integer userId, Date startDate, Date endDate, String bookName);
}
