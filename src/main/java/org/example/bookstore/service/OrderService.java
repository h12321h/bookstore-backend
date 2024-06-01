package org.example.bookstore.service;

import org.example.bookstore.dto.*;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {
    List<OrderDto> findByUserId(int userId);
    Boolean saveOrder(List<BuyItem> buyItemList, Integer userId, String name, String address, String phone);
    void deleteOrder(Integer id);

    List<OrderDto> findByUserIdAndBookName(Integer userId, String bookName);
    List<OrderDto> findByUserIdAndDate(Integer userId, Date startDate, Date endDate);
    List<OrderDto> findByUserIdAndBookNameAndDate(Integer userId, String bookName, Date startDate, Date endDate);

    List<BookStatisticDto> getBookStatistic(Integer userId, Date startDate, Date endDate);

    List<OrderDto> findAll(Pageable pageable);

    List<OrderDto> findByBookName(Pageable pageable, String bookName);

    List<OrderDto> findByDate(Pageable pageable, Date startDate, Date endDate);

    List<OrderDto> findByBookNameAndDate(Pageable pageable, String bookName, Date startDate, Date endDate);

    Integer getOrdersNum();

    Integer getOrdersNumByBookName(String bookName);

    Integer getOrdersNumByDate(Date startDate, Date endDate);

    Integer getOrdersNumByBookNameAndDate(String bookName, Date startDate, Date endDate);


    List<BookRangeDto> getRangeBook(Pageable pageable, Date startDate, Date endDate);

    Integer getRangeBookNum(Date startDate, Date endDate);

    List<UserRangeDto> getRangeUser(Pageable pageable, Date startDate, Date endDate);

    Integer getRangeUserNum(Date startDate, Date endDate);

    Integer getBookStatisticNum(Integer userId, Date startDate, Date endDate);

    BigDecimal getBookStatisticPrice(Integer userId, Date startDate, Date endDate);
}
