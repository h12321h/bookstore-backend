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
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Login: " + username);
        System.out.println("Password: " + password);
        if (userService.checkUser(username, password)) {
            User user= userService.findUserByUsername(username);
            Integer id = user.getId();
            Boolean isAdmin = user.getType();
            if(user.getBanned()){
                return -1;
            }
            //System.out.println("User ID: " + id);
            SessionUtils.setSession(id,isAdmin);
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
    @ResponseBody
    public Map<String, Boolean> checkLogin() {
        HttpSession session = SessionUtils.getSession();
        boolean isLogin = session != null && session.getAttribute("userId") != null;
        return Map.of("isLogin", isLogin);
    }

//    export async function checkIdentity() {
//        return fetch(`${PREFIX}/check_identity`, {
//            method: 'GET',
//                    credentials: 'include'
//        })
//        .then(response => response.json())
//        .catch(error => console.error('Error checking identity:', error));
//    }

    @GetMapping("/check_identity")
    @ResponseBody
    public Map<String,Boolean> checkIdentity() {
        HttpSession session = SessionUtils.getSession();
        Boolean  isAdmin = session != null && session.getAttribute("userId") != null && session.getAttribute("isAdmin") == Boolean.TRUE;
        return Map.of("isAdmin", isAdmin);
    }

}
