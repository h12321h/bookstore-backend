package org.example.bookstore.controller;

import org.example.bookstore.entity.User;
import org.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Login: " + username);
        System.out.println("Password: " + password);
        if (userService.checkUser(username, password)) {
            return userService.findUserByUsername(username).getId();
        } else {
            return 0;
        }
    }

    @PostMapping(value = "/user")
    public User findUser(@RequestBody Integer id) {
        System.out.println("Searching User: " + id);
        return userService.findUserById(id);
    }
}
