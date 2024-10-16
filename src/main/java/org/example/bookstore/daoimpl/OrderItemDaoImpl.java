package org.example.bookstore.daoimpl;

import org.example.bookstore.dao.OrderItemDao;
import org.example.bookstore.entity.OrderItem;
import org.example.bookstore.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> findByOrderId(int orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    @Override
    @Transactional//(propagation = Propagation.REQUIRES_NEW)
    public void saveOrderItem(OrderItem orderItem) {
        //int result=10/0;
        orderItemRepository.save(orderItem);
    }
}
