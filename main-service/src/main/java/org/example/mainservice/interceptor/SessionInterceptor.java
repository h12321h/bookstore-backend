package org.example.mainservice.interceptor;
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
        if (session != null) {
           // System.out.println(session.getId());
           // System.out.println("SessionInterceptor is invoked");
           // System.out.println("Session ID: " + session.getAttribute("userId"));

            // 检查是否存在 userId
            if (session.getAttribute("userId") != null) {
                // 检查是否为/admin/*的请求
                String requestURI = request.getRequestURI();
                if (requestURI.startsWith("/admin/")) {
                    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
                    if (isAdmin != null && isAdmin) {
                        // isAdmin存在且为true，允许请求继续
                        return true;
                    } else {
                        // isAdmin不存在或为false，拒绝访问
                        // System.out.println("Admin access denied");
                        response.setStatus(403);
                        return false;
                    }
                }
                // 非/admin/*请求，允许请求继续
                return true;
            }
        }

       // System.out.println("Session is not logged in yet");
        response.setStatus(401);
        return false;
    }
}