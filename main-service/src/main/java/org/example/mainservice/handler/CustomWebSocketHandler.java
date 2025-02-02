package org.example.mainservice.handler;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.example.mainservice.utils.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value = "/ws-orders", configurator = CustomWebSocketHandler.CustomConfigurator.class)
public class CustomWebSocketHandler {

    private static WebSocketSessionManager sessionManager;

    // 无参构造函数
    public CustomWebSocketHandler() {
    }

    // 构造函数注入WebSocketSessionManager
    @Autowired
    public CustomWebSocketHandler(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        String userId = getUserIdFromSession(config);
        sessionManager.addSession(userId, session); // 添加用户的标准 WebSocket Session
        System.out.println("用户 " + userId + " 已连接");
    }

    @OnClose
    public void onClose(Session session) {
        String userId = sessionManager.getUserIdBySession(session);
        sessionManager.removeSession(userId); // 移除用户的标准 WebSocket Session
        System.out.println("用户 " + userId + " 已断开连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到消息: " + message);
    }

    private String getUserIdFromSession(EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("HTTP_SESSION");
        if (httpSession != null) {
            Integer userId = (Integer) httpSession.getAttribute("userId");
            if (userId != null) {
                return userId.toString();
            }
        }
        return null;
    }

    public static class CustomConfigurator extends ServerEndpointConfig.Configurator {
        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, jakarta.websocket.HandshakeResponse response) {
            HttpSession httpSession = (HttpSession) request.getHttpSession();
            sec.getUserProperties().put("HTTP_SESSION", httpSession);
        }
    }
}


//package org.example.mainservice.handler;
//
//import jakarta.servlet.http.HttpSession;
//import org.example.mainservice.utils.WebSocketSessionManager;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//@Component
//public class CustomWebSocketHandler extends TextWebSocketHandler {
//
//    private WebSocketSessionManager sessionManager;
//
//    public CustomWebSocketHandler(WebSocketSessionManager sessionManager) {
//        this.sessionManager = sessionManager;
//    }
//
//    // 当 WebSocket 连接建立时调用
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        String userId = getUserIdFromSession(session);
//        sessionManager.addSession(userId, session);  // 添加用户的 WebSocketSession
//       // System.out.println("用户 " + userId + " 已连接");
//    }
//
//    // 当 WebSocket 连接关闭时调用
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        String userId = getUserIdFromSession(session);
//        sessionManager.removeSession(userId);  // 移除用户的 WebSocketSession
//        //System.out.println("用户 " + userId + " 已断开连接");
//    }
//
//    // 处理接收到的消息
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // 这里可以处理收到的消息
//        //System.out.println("收到消息: " + message.getPayload());
//    }
//
//    // 获取用户ID的方法，可以从 session 或者 token 中提取
//    private String getUserIdFromSession(WebSocketSession session) {
//        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
//       // HttpSession session = SessionUtils.getSession();
//        if (httpSession != null) {
//            // 使用 SessionUtils 从 HttpSession 中获取 userId
//            Integer userId = (Integer) httpSession.getAttribute("userId");
//            //System.out.println("GET userId: " + userId);
//            if (userId != null) {
//                return userId.toString();
//            }
//        }
//        return null; // 如果 userId 不存在或 session 为空，返回 null
//    }
//}
