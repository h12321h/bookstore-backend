package org.example.mainservice.utils;

import jakarta.websocket.Session;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionManager {

    // 使用线程安全的 ConcurrentHashMap 来存储每个用户的 WebSocket Session
    private ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    // 添加一个新的 WebSocket 连接，当客户端连接时调用
    public void addSession(String userId, Session session) {
        sessionMap.put(userId, session);
    }

    // 根据 userId 获取 WebSocket Session
    public Session getSession(String userId) {
        return sessionMap.get(userId);
    }

    // 移除 WebSocket Session，当客户端断开连接时调用
    public void removeSession(String userId) {
        sessionMap.remove(userId);
    }

    // 向特定用户发送消息
    public void sendMessageToUser(String userId, String message) throws IOException {
        Session session = sessionMap.get(userId);
        if (session != null && session.isOpen()) {
            session.getBasicRemote().sendText(message);  // 使用 Jakarta WebSocket 的方式发送文本消息
        }
    }

    // 向所有用户广播消息（可选功能）
    public void broadcastMessage(String message) throws IOException {
        for (Session session : sessionMap.values()) {
            if (session != null && session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        }
    }

    // 根据 Session 查找对应的 userId
    public String getUserIdBySession(Session session) {
        for (String key : sessionMap.keySet()) {
            if (sessionMap.get(key).equals(session)) {
                return key;
            }
        }
        return null;
    }
}
