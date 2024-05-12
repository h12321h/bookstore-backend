package org.example.bookstore.serviceimpl;

import org.example.bookstore.dao.OrderDao;
import org.example.bookstore.dao.OrderItemDao;
import org.example.bookstore.dao.UserDao;
import org.example.bookstore.dto.BuyItem;
import org.example.bookstore.entity.Order;
import org.example.bookstore.entity.OrderItem;
import org.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Order> findByUserId(int userId) {
        return orderDao.findByUserId(userId);
    }

    @Override
    public void saveOrder(List<BuyItem> buyItemList, Integer userId) {
        Order order = new Order();
        Float totalPrice = 0f;
        order.setUserId(userId);
        order.setUser(userDao.findById(userId));
        order.setTotalPrice(totalPrice);
        orderDao.saveOrder(order);
        if (buyItemList != null) {
            for (BuyItem buyItem : buyItemList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setBookId(buyItem.getBookId());
                orderItem.setQuantity(buyItem.getQuantity());
                orderItem.setPrice(buyItem.getPrice());
                orderItem.setOrderId(order.getId());
                orderItem.setOrder(order);
                orderItemDao.saveOrderItem(orderItem);
                totalPrice += orderItem.getPrice() * orderItem.getQuantity();
            }
        }
        order.setTotalPrice(totalPrice);
        orderDao.saveOrder(order);
    }
}
