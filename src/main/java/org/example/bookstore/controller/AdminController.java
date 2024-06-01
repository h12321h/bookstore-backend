package org.example.bookstore.controller;

import org.example.bookstore.dto.*;
import org.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import org.example.bookstore.entity.User;
import org.example.bookstore.service.UserService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/users")
    public List<UserDto> getUserList(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getUserList(pageable);
    }

    @GetMapping("/admin/users/num")
    public Integer getUsersNum() {
        return userService.getUsersNum();
    }

    @PutMapping("/admin/ban/{id}")
    public String banUser(@PathVariable int id) {
        if (userService.banUser(id))
            return "success";
        else
            return "fail";
    }

    @PutMapping("/admin/lift/{id}")
    public String liftUser(@PathVariable int id) {
        if (userService.liftUser(id))
            return "success";
        else
            return "fail";
    }


    @PostMapping("/admin/orders")
    public List<OrderDto> getOrders(@RequestBody ScreenOderRequest request, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        //解析，分类
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
        String bookName = request.getBookName();
        System.out.println("startDate:" + startDate);
        System.out.println("endDate:" + endDate);
        System.out.println("bookName:" + bookName);
        if (startDate == null && endDate == null && bookName.isEmpty())
            return orderService.findAll(pageable);
        if (startDate == null && endDate == null && !bookName.isEmpty())
            return orderService.findByBookName(pageable, bookName);
        if (bookName.isEmpty() && startDate != null && endDate != null) {
            return orderService.findByDate(pageable, startDate, endDate);
        }
        return orderService.findByBookNameAndDate(pageable, bookName, startDate, endDate);
    }


    @PostMapping("/admin/orders/num")
    public Integer getOrdersNum(@RequestBody ScreenOderRequest request) {
        //解析，分类
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
        String bookName = request.getBookName();
        if (startDate == null && endDate == null && bookName.isEmpty())
            return orderService.getOrdersNum();
        if (startDate == null && endDate == null && !bookName.isEmpty())
            return orderService.getOrdersNumByBookName(bookName);
        if (bookName.isEmpty() && startDate != null && endDate != null) {
            return orderService.getOrdersNumByDate(startDate, endDate);
        }
        return orderService.getOrdersNumByBookNameAndDate(bookName, startDate, endDate);
    }


    @PostMapping("/admin/statistic/book")
    public List<BookRangeDto> getBookStatisticList(@RequestBody ScreenOderRequest request, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        //解析，分类
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
        return orderService.getRangeBook(pageable, startDate, endDate);
    }

    @PostMapping("/admin/statistic/book/num")
    public Integer getBookStatisticNum(@RequestBody ScreenOderRequest request) {
        //解析，分类
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
        return orderService.getRangeBookNum(startDate, endDate);
    }

    @PostMapping("/admin/statistic/user")
    public List<UserRangeDto> getUserStatisticList(@RequestBody ScreenOderRequest request, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        //解析，分类
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
        return orderService.getRangeUser(pageable, startDate, endDate);
    }

    @PostMapping("/admin/statistic/user/num")
    public Integer getUserStatisticNum(@RequestBody ScreenOderRequest request) {
        //解析，分类
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
        return orderService.getRangeUserNum(startDate, endDate);
    }

}
