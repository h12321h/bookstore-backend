package org.example.bookstore.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderResultListener {

    // 监听 order_result_topic 主题中的消息
    @KafkaListener(topics = "order_result_topic", groupId = "order_result_group")
    public void listenOrderResultMessage(String message) {
        // 打印收到的处理结果到控制台
        System.out.println("收到订单处理结果: " + message);
    }
}
