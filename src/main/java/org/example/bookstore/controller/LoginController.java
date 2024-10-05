package org.example.bookstore.controller;

import org.example.bookstore.entity.User;
import org.example.bookstore.service.TimerService;
import org.example.bookstore.service.UserService;
import org.example.bookstore.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.WebApplicationContext;

import java.time.Duration;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private WebApplicationContext applicationContext; // 动态获取 Bean 的上下文

    @PostMapping("/login")
    public Integer login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("Login: " + username);
        System.out.println("Password: " + password);
        UserService userService = applicationContext.getBean(UserService.class); // 动态获取 UserService

        if (userService.checkUser(username, password)) {
            User user= userService.findUserByUsername(username);
            Integer id = user.getId();
            Boolean isAdmin = user.getType();
            if(user.getBanned()){
                return -1;
            }
            // 开始会话计时
            TimerService timerService = applicationContext.getBean(TimerService.class); // 动态获取 TimerService
            timerService.startTimer();
            //System.out.println("User ID: " + id);
            SessionUtils.setSession(id,isAdmin);
            return id;
        } else {
            return 0;
        }
    }

    @PostMapping("/logout")
    public long logout(HttpServletRequest request) {
        HttpSession session = SessionUtils.getSession();
        if (session != null) {
            // 停止计时器并获取会话时间
            TimerService timerService = applicationContext.getBean(TimerService.class); // 动态获取 TimerService
            Duration sessionDuration = timerService.stopTimer();

            // 清空session
            session.invalidate();

            // 返回会话持续时间（以分钟为单位）
            return sessionDuration.toMinutes();
        }
        return 0;
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
