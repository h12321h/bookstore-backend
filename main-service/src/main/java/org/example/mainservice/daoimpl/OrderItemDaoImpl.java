package org.example.mainservice.daoimpl;

import org.example.mainservice.dao.OrderItemDao;
import org.example.mainservice.entity.OrderItem;
import org.example.mainservice.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
