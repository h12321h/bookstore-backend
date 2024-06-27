package org.example.bookstore.serviceimpl;

import org.example.bookstore.dao.OrderDao;
import org.example.bookstore.dao.UserDao;
import org.example.bookstore.dao.BookDao;
import org.example.bookstore.dto.*;
import org.example.bookstore.entity.Order;
import org.example.bookstore.entity.OrderItem;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

//    @Autowired
//    private OrderItemDao orderItemDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    private void toDto(List<Order> orderList, List<OrderDto> orderDtoList){
        for(Order order: orderList){
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setDate(order.getOrderDate());
            orderDto.setStatus(order.getStatus());
            orderDto.setName(order.getName());
            orderDto.setAddress(order.getAddress());
            orderDto.setPhone(order.getPhone());
            List<OrderItemDto> orderItemDtoList = new ArrayList<>();
            List<OrderItem> orderItemList = order.getOrderItems();
            for(OrderItem orderItem: orderItemList){
                Book book = orderItem.getBook();
                OrderItemDto orderItemDto = new OrderItemDto(book.getTitle(), orderItem.getPrice(), orderItem.getQuantity());
                orderItemDtoList.add(orderItemDto);
            }
            orderDto.setItems(orderItemDtoList);
            orderDtoList.add(orderDto);
        }
    }

    @Override
    public List<OrderDto> findByUserId(int userId, Pageable pageable) {
        //初始化返回值
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserId(userId, pageable).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public Boolean saveOrder(List<BuyItem> buyItemList, Integer userId, String name, String address, String phone ) {
        Order order = new Order();
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        order.setUser(userDao.findById(userId));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(new java.util.Date());
        order.setName(name);
        order.setAddress(address);
        order.setPhone(phone);
        order.setStatus("已完成");
        //检查库存
        for(BuyItem buyItem: buyItemList){
            if(!bookDao.checkStock(buyItem.getBookId(), buyItem.getQuantity())){
                return false;
            }
        }
        orderDao.saveOrder(order);
        List<OrderItem> orderItemList = new ArrayList<>();

        if (buyItemList != null) {
            for (BuyItem buyItem : buyItemList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setPrice(buyItem.getPrice());
                orderItem.setOrder(order);
                orderItem.setBook(bookDao.findBookById(buyItem.getBookId()));
                //orderItemDao.saveOrderItem(orderItem);
                if(!bookDao.decreaseStock(buyItem.getBookId(), buyItem.getQuantity())){
                    return false;
                }
                orderItemList.add(orderItem);
                totalPrice = totalPrice.add(buyItem.getPrice().multiply(BigDecimal.valueOf(buyItem.getQuantity())));
               // totalPrice+=orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            }
        }
        order.setOrderItems(orderItemList);
        order.setTotalPrice(totalPrice);
        orderDao.saveOrder(order);
        return true;
    }

    @Override
    public void deleteOrder(Integer id) {
        orderDao.deleteById(id);
    }
    
    @Override
    public List<OrderDto> findByUserIdAndBookName(Integer userId, String bookName, Pageable pageable) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndBookName(userId, bookName, pageable).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByUserIdAndDate(Integer userId, Date startDate, Date endDate, Pageable pageable) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndDate(userId, startDate, endDate, pageable).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByUserIdAndBookNameAndDate(Integer userId, String bookName, Date startDate, Date endDate, Pageable pageable) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndBookNameAndDate(userId, bookName, startDate, endDate, pageable).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<BookStatisticDto> getBookStatistic(Integer userId, Date startDate, Date endDate) {
        List<BookStatisticDto> bookStatisticDtoList = new ArrayList<>();
        List<Object[]> bookList = orderDao.getBookStatisticByUserId(userId, startDate, endDate);
        Integer i = 0;
        for(Object[] book: bookList){
            BookStatisticDto bookStatisticDto = new BookStatisticDto();
            bookStatisticDto.setName((String)book[0]);
            bookStatisticDto.setQuantity((Long)book[1]);
            bookStatisticDto.setPrice((BigDecimal)book[2]);
            bookStatisticDto.setKey(++i);
            bookStatisticDtoList.add(bookStatisticDto);
        }
        return bookStatisticDtoList;
    }

    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findAll(pageable).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByBookName(Pageable pageable, String bookName) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByBookName(pageable, bookName).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByDate(Pageable pageable, Date startDate, Date endDate) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByDate(pageable, startDate, endDate).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByBookNameAndDate(Pageable pageable, String bookName, Date startDate, Date endDate) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByBookNameAndDate(pageable, bookName, startDate, endDate).getContent();
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public Integer getOrdersNumByUserIdAndDate(Integer userId, Date startDate, Date endDate) {
        return orderDao.getOrdersNumByUserIdAndDate(userId, startDate, endDate);
    }

    @Override
    public Integer getOrdersNumByUserIdAndDateAndBookName(Integer userId, Date startDate, Date endDate, String bookName) {
        return orderDao.getOrdersNumByUserIdAndDateAndBookName(userId, startDate, endDate, bookName);
    }

    @Override
    public Integer getOrdersNum() {
        return orderDao.getOrdersNum();
    }

    @Override
    public Integer getOrdersNumByBookName(String bookName) {
        return orderDao.getOrdersNumByBookName(bookName);
    }

    @Override
    public Integer getOrdersNumByDate(Date startDate, Date endDate) {
        return orderDao.getOrdersNumByDate(startDate, endDate);
    }

    @Override
    public Integer getOrdersNumByBookNameAndDate(String bookName, Date startDate, Date endDate) {
        return orderDao.getOrdersNumByBookNameAndDate(bookName, startDate, endDate);
    }

    @Override
    public List<BookRangeDto> getRangeBook(Pageable pageable, Date startDate, Date endDate) {
        List<BookRangeDto> bookRangeDtoList = new ArrayList<>();
        List<Object[]> bookList = orderDao.getRangeBook(pageable, startDate, endDate);
        for(Object[] book: bookList){
            BookRangeDto bookRangeDto = new BookRangeDto();
            bookRangeDto.setTitle((String)book[0]);
            bookRangeDto.setQuantity((Long)book[1]);
            bookRangeDto.setRange((Long)book[2]);
            bookRangeDtoList.add(bookRangeDto);
        }
        return bookRangeDtoList;
    }

    @Override
    public Integer getRangeBookNum(Date startDate, Date endDate) {
        return orderDao.getRangeBookNum(startDate, endDate);
    }

    @Override
    public List<UserRangeDto> getRangeUser(Pageable pageable, Date startDate, Date endDate) {
        List<UserRangeDto> userRangeDtoList = new ArrayList<>();
        List<Object[]> userList = orderDao.getRangeUser(pageable, startDate, endDate);
        for(Object[] user: userList){
            UserRangeDto userRangeDto = new UserRangeDto();
            userRangeDto.setUsername((String)user[0]);
            userRangeDto.setContact((String)user[1]);
            userRangeDto.setPrice((BigDecimal) user[2]);
            userRangeDto.setRange((Long)user[3]);
            userRangeDtoList.add(userRangeDto);
        }
        return userRangeDtoList;
    }

    @Override
    public Integer getRangeUserNum(Date startDate, Date endDate) {
        return orderDao.getRangeUserNum(startDate, endDate);
    }

    @Override
    public Integer getBookStatisticNum(Integer userId, Date startDate, Date endDate) {
        return orderDao.getBookStatisticNum(userId, startDate, endDate);
    }

    @Override
    public BigDecimal getBookStatisticPrice(Integer userId, Date startDate, Date endDate) {
        return orderDao.getBookStatisticPrice(userId, startDate, endDate);
    }

}
