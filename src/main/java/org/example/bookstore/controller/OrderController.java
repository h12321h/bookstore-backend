package org.example.bookstore.controller;

import org.example.bookstore.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.example.bookstore.entity.Order;
import org.example.bookstore.service.OrderService;

import org.example.bookstore.dto.AddOrderRequest;
import org.example.bookstore.dto.BuyItem;

import java.util.List;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public List<OrderDto> getOrders(@RequestBody Integer user_id) {
        return orderService.findByUserId(user_id);
    }

    @PostMapping("/order/add")
    public String addOrder(@RequestBody AddOrderRequest request) {
        //打印request
        Integer user_id = request.getUserId();
        List<BuyItem> buyItems = request.getItems();
        String name = request.getName();
        String address = request.getAddress();
        String phone = request.getPhone();
        orderService.saveOrder(buyItems,user_id,name,address,phone);
        return "订单确认";
    }

    @PostMapping("/order/delete")
    public void deleteOrder(@RequestBody Integer id) {
        orderService.deleteOrder(id);
    }
}
