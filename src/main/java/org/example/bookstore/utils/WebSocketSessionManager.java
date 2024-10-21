package org.example.bookstore.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionManager {

    // 使用线程安全的 ConcurrentHashMap 来存储每个用户的 WebSocketSession
    private ConcurrentHashMap<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    // 添加一个新的 WebSocket 连接，当客户端连接时调用
    public void addSession(String userId, WebSocketSession session) {
        //System.out.println("ADD connect: " + userId + session);
        sessionMap.put(userId, session);
       // System.out.println("ADD sessionMap: " + sessionMap);
    }

    // 根据 userId 获取 WebSocketSession
    public WebSocketSession getSession(String userId) {
        return sessionMap.get(userId);
    }

    // 移除 WebSocketSession，当客户端断开连接时调用
    public void removeSession(String userId) {
      //  System.out.println("REMOVE session: " + userId);
        sessionMap.remove(userId);
    }

    // 向特定用户发送消息
    public void sendMessageToUser(String userId, String message) throws IOException {
        System.out.println("sessionMap: " + sessionMap);
        WebSocketSession session = sessionMap.get(userId);
        System.out.println("SEND session: " +userId + session);
        if (session != null && session.isOpen()) {
            System.out.println("SEND message: " + message);
            session.sendMessage(new TextMessage(message));
        }
    }

    // 向所有用户广播消息（可选功能）
    public void broadcastMessage(String message) throws IOException {
        for (WebSocketSession session : sessionMap.values()) {
            if (session != null && session.isOpen()) {
                session.sendMessage(new TextMessage(message));
            }
        }
    }
}
