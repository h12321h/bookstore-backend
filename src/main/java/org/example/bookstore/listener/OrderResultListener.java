package org.example.bookstore.listener;

import org.example.bookstore.utils.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OrderResultListener {

    // 注入 WebSocket 消息发送工具类
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    private WebSocketSessionManager sessionManager;

    public OrderResultListener(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    // 监听 Kafka 的 "order_result_topic" 主题
    @KafkaListener(topics = "order_result_topic", groupId = "order_result_group")
    public void listenOrderResultMessage(String message) {
        // 当监听到消息时，打印消息
        System.out.println("收到订单处理结果: " + message);

        String[] messageParts = message.split(":");
        String userId = messageParts[0];
        String orderResult = messageParts[1];

        // 通过 WebSocket 向前端发送消息
//        messagingTemplate.convertAndSend("/topic/orderResult/" + userId, orderResult);

        // 使用 WebSocket 向特定用户发送订单处理结果
        try {
            sessionManager.sendMessageToUser(userId, orderResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
