package org.example.bookstore.serviceimpl;

import org.example.bookstore.dao.OrderDao;
import org.example.bookstore.dao.OrderItemDao;
import org.example.bookstore.dao.UserDao;
import org.example.bookstore.dao.BookDao;
import org.example.bookstore.dto.BuyItem;
import org.example.bookstore.dto.OrderDto;
import org.example.bookstore.dto.OrderItemDto;
import org.example.bookstore.entity.Order;
import org.example.bookstore.entity.OrderItem;
import org.example.bookstore.entity.Book;
import org.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

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
    public List<OrderDto> findByUserId(int userId) {
        //初始化返回值
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserId(userId);
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
        orderDao.saveOrder(order);
        if (buyItemList != null) {
            for (BuyItem buyItem : buyItemList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setPrice(buyItem.getPrice());
                orderItem.setOrder(order);
                orderItem.setBook(bookDao.findBookById(buyItem.getBookId()));
                orderItemDao.saveOrderItem(orderItem);
                if(!bookDao.decreaseStock(buyItem.getBookId(), buyItem.getQuantity())){
                    return false;
                }
                totalPrice = totalPrice.add(buyItem.getPrice().multiply(BigDecimal.valueOf(buyItem.getQuantity())));
               // totalPrice+=orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            }
        }
        order.setTotalPrice(totalPrice);
        orderDao.saveOrder(order);
        return true;
    }

    @Override
    public void deleteOrder(Integer id) {
        orderDao.deleteById(id);
    }
    
    @Override
    public List<OrderDto> findByUserIdAndBookName(Integer userId, String bookName) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndBookName(userId, bookName);
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByUserIdAndDate(Integer userId, Date startDate, Date endDate) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndDate(userId, startDate, endDate);
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }

    @Override
    public List<OrderDto> findByUserIdAndBookNameAndDate(Integer userId, String bookName, Date startDate, Date endDate) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserIdAndBookNameAndDate(userId, bookName, startDate, endDate);
        toDto(orderList, orderDtoList);
        return orderDtoList;
    }
}
