package org.example.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.example.bookstore.dto.OrderDto;
import org.example.bookstore.dto.ScreenOderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.example.bookstore.entity.Order;
import org.example.bookstore.service.OrderService;

import org.example.bookstore.dto.AddOrderRequest;
import org.example.bookstore.dto.BuyItem;

import org.example.bookstore.utils.SessionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public List<OrderDto> getOrders(@RequestBody ScreenOderRequest request) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id=0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
           // System.out.println("order  ID: " + user_id);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            String startDateString = request.getStartDate();
            if (startDateString != null && !startDateString.isEmpty()) {
                startDate = formatter.parse(startDateString);
            }
        } catch (ParseException e) {
            startDate = null;
        }
        try {
            String endDateString = request.getEndDate();
            if (endDateString != null && !endDateString.isEmpty()) {
                endDate = formatter.parse(endDateString);
            }
        } catch (ParseException e) {
            endDate = null;
        }
        String bookName=request.getBookName();
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        System.out.println("bookName:"+bookName);
        if(startDate==null&&endDate==null&&bookName.isEmpty())
            return orderService.findByUserId(user_id);
        if(startDate==null&&endDate==null&&!bookName.isEmpty())
            return orderService.findByUserIdAndBookName(user_id,bookName);
        if(bookName.isEmpty()&&startDate!=null&&endDate!=null){
            System.out.println("startDate:"+startDate);
            return orderService.findByUserIdAndDate(user_id,startDate,endDate);
        }
        return orderService.findByUserIdAndBookNameAndDate(user_id,bookName,startDate,endDate);
        //return orderService.findByUserId(user_id);
    }

    @PostMapping("/order/add")
    public String addOrder(@RequestBody AddOrderRequest request) {
        //打印request
        Integer user_id = request.getUserId();
        //靠session获取id
        HttpSession session = SessionUtils.getSession();
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
        }
        List<BuyItem> buyItems = request.getItems();
        String name = request.getName();
        String address = request.getAddress();
        String phone = request.getPhone();
        if(!orderService.saveOrder(buyItems,user_id,name,address,phone)){
            return "stockout";
        }
        System.out.println("addOrder");
        return "success";
    }

    @PostMapping("/order/delete")
    public void deleteOrder(@RequestBody Integer id) {
        orderService.deleteOrder(id);
    }
}
