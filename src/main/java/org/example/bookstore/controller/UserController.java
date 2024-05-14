package org.example.bookstore.controller;

import jakarta.servlet.http.HttpSession;
import org.example.bookstore.entity.User;
import org.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.bookstore.utils.SessionUtils;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Login: " + username);
        System.out.println("Password: " + password);
        if (userService.checkUser(username, password)) {
            Integer id = userService.findUserByUsername(username).getId();
            System.out.println("User ID: " + id);
            SessionUtils.setSession(id);
            //System.out.println("Session ID: " + session.getAttribute("userId").toString());
            return id;
        } else {
            return 0;
        }
    }

    @PostMapping(value = "/user")
    public User findUser(@RequestBody Integer id) {
        HttpSession session = SessionUtils.getSession();
        if (session != null ) {
            System.out.println("book Session ID: " + session.getId());
        }
        System.out.println("Searching User: " + id);
        return userService.findUserById(id);
    }
}
