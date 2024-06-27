//package org.example.bookstore.daoimpl;
//
//import org.example.bookstore.dao.OrderItemDao;
//import org.example.bookstore.entity.OrderItem;
//import org.example.bookstore.repository.OrderItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class OrderItemDaoImpl implements OrderItemDao {
//    @Autowired
//    private OrderItemRepository orderItemRepository;
//
//    @Override
//    public List<OrderItem> findByOrderId(int orderId) {
//        return orderItemRepository.findByOrderId(orderId);
//    }
//
//    @Override
//    public void saveOrderItem(OrderItem orderItem) {
//        orderItemRepository.save(orderItem);
//    }
//}
