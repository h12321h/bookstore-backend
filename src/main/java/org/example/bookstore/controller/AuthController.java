package org.example.bookstore.controller;

import org.example.bookstore.entity.User;
import org.example.bookstore.service.UserService;
import org.example.bookstore.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Login: " + username);
        System.out.println("Password: " + password);
        if (userService.checkUser(username, password)) {
            User user= userService.findUserByUsername(username);
            Integer id = user.getId();
            if(user.getBanned()){
                return -1;
            }
            //System.out.println("User ID: " + id);
            SessionUtils.setSession(id);
            return id;
        } else {
            return 0;
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            //清空session
            session.invalidate();

        }
        return "success";
    }

    @GetMapping("/check_login")
    public Map<String, Boolean> checkLogin() {
        HttpSession session = SessionUtils.getSession();
        boolean isLogin = session != null && session.getAttribute("userId") != null;
        return Map.of("isLogin", isLogin);
    }
}
