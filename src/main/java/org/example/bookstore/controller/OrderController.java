package org.example.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.example.bookstore.dao.BookDao;
import org.example.bookstore.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.example.bookstore.entity.Order;
import org.example.bookstore.service.OrderService;

import org.example.bookstore.utils.SessionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private BookDao bookDao;

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
        Integer page=request.getPage();
        Integer size=request.getSize();
        Pageable pageable = PageRequest.of(page, size);
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        System.out.println("bookName:"+bookName);
        if(startDate==null&&endDate==null&&bookName.isEmpty())
            return orderService.findByUserId(user_id,pageable);
        if(startDate==null&&endDate==null&&!bookName.isEmpty())
            return orderService.findByUserIdAndBookName(user_id,bookName,pageable);
        if(bookName.isEmpty()&&startDate!=null&&endDate!=null){
            System.out.println("startDate:"+startDate);
            return orderService.findByUserIdAndDate(user_id,startDate,endDate,pageable);
        }
        return orderService.findByUserIdAndBookNameAndDate(user_id,bookName,startDate,endDate,pageable);
        //return orderService.findByUserId(user_id);
    }

    @PostMapping("/orders/num")
    public Integer getOrdersNum(@RequestBody ScreenOderRequest request) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id=0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            String startDateString = request.getStartDate();
            if (startDateString != null && !startDateString.isEmpty()) {
                startDate = formatter.parse(startDateString);
            }else{
                startDate = formatter.parse("1970-01-01");
            }
        } catch (ParseException e) {
            startDate = null;
        }
        try {
            String endDateString = request.getEndDate();
            if (endDateString != null && !endDateString.isEmpty()) {
                endDate = formatter.parse(endDateString);
            }else{
                endDate = formatter.parse("2100-01-01");
            }
        } catch (ParseException e) {
            endDate = null;
        }
        String bookName=request.getBookName();
        if(bookName.isEmpty()){
            return orderService.getOrdersNumByUserIdAndDate(user_id,startDate,endDate);
        }
        else return orderService.getOrdersNumByUserIdAndDateAndBookName(user_id,startDate,endDate,bookName);

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

    @PostMapping("/order/statistic")
    public List<BookStatisticDto> getOrderBookStatistic(@RequestBody ScreenOderRequest request) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id=0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            String startDateString = request.getStartDate();
            if (startDateString != null && !startDateString.isEmpty()) {
                startDate = formatter.parse(startDateString);
            }else{
                startDate = formatter.parse("1970-01-01");
            }
        } catch (ParseException e) {
            startDate = null;
        }
        try {
            String endDateString = request.getEndDate();
            if (endDateString != null && !endDateString.isEmpty()) {
                endDate = formatter.parse(endDateString);
            }else{
                endDate = formatter.parse("2100-01-01");
            }
        } catch (ParseException e) {
            endDate = null;
        }
        return orderService.getBookStatistic(user_id,startDate,endDate);
    }

    @PostMapping("/order/statistic/num")
    public Integer getOrderBookStatisticNum(@RequestBody ScreenOderRequest request) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id=0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            String startDateString = request.getStartDate();
            if (startDateString != null && !startDateString.isEmpty()) {
                startDate = formatter.parse(startDateString);
            }else{
                startDate = formatter.parse("1970-01-01");
            }
        } catch (ParseException e) {
            startDate = null;
        }
        try {
            String endDateString = request.getEndDate();
            if (endDateString != null && !endDateString.isEmpty()) {
                endDate = formatter.parse(endDateString);
            }else{
                endDate = formatter.parse("2100-01-01");
            }
        } catch (ParseException e) {
            endDate = null;
        }
        return orderService.getBookStatisticNum(user_id,startDate,endDate);
    }

    @PostMapping("/order/statistic/price")
    public BigDecimal getOrderBookStatisticPrice(@RequestBody ScreenOderRequest request) {
        HttpSession session = SessionUtils.getSession();
        Integer user_id=0;
        if (session != null ) {
            user_id = (Integer) session.getAttribute("userId");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            String startDateString = request.getStartDate();
            if (startDateString != null && !startDateString.isEmpty()) {
                startDate = formatter.parse(startDateString);
            }else{
                startDate = formatter.parse("1970-01-01");
            }
        } catch (ParseException e) {
            startDate = null;
        }
        try {
            String endDateString = request.getEndDate();
            if (endDateString != null && !endDateString.isEmpty()) {
                endDate = formatter.parse(endDateString);
            }else{
                endDate = formatter.parse("2100-01-01");
            }
        } catch (ParseException e) {
            endDate = null;
        }
        return orderService.getBookStatisticPrice(user_id,startDate,endDate);
    }
}
