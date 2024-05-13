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

import java.util.ArrayList;
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

    @Override
    public List<OrderDto> findByUserId(int userId) {
        //初始化返回值
        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderDao.findByUserId(userId);
        for(Order order: orderList){
            //初始化orderdto
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setTotalPrice(order.getTotalPrice());
            orderDto.setDate(order.getOrderDate());
            orderDto.setStatus(order.getStatus());
            //初始化orderitemdtolist
            List<OrderItemDto> orderItemDtoList = new ArrayList<>();
            List<OrderItem> orderItemList = orderItemDao.findByOrderId(order.getId());
            for(OrderItem orderItem: orderItemList){
                Book book = bookDao.findBookById(orderItem.getBookId());
                OrderItemDto orderItemDto = new OrderItemDto(book.getTitle(), orderItem.getPrice(), orderItem.getQuantity());
                orderItemDtoList.add(orderItemDto);
            }
            orderDto.setItems(orderItemDtoList);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public void saveOrder(List<BuyItem> buyItemList, Integer userId) {
        Order order = new Order();
        Float totalPrice = 0f;
        order.setUserId(userId);
        order.setUser(userDao.findById(userId));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(new java.util.Date());
        order.setStatus("已完成");
        orderDao.saveOrder(order);
        if (buyItemList != null) {
            for (BuyItem buyItem : buyItemList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setBookId(buyItem.getBookId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setPrice(buyItem.getPrice());
                orderItem.setOrderId(order.getId());
                orderItem.setOrder(order);
                orderItem.setBook(bookDao.findBookById(buyItem.getBookId()));
                orderItemDao.saveOrderItem(orderItem);
                totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            }
        }
        order.setTotalPrice(totalPrice);
        orderDao.saveOrder(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderDao.deleteById(id);
    }
}
