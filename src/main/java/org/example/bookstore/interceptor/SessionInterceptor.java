package org.example.bookstore.interceptor;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession(false);
        System.out.println(session.getId());
        System.out.println("SessionInterceptor is invoked");
        System.out.println("Session ID: " + session.getAttribute("userId"));
        if (session != null && session.getAttribute("userId") != null) {
            // session中存在userId，允许请求继续
            return true;
        }
        System.out.println("Session is not logged in yet");
        response.setStatus(401);
        return false;
    }
}