package org.example.bookstore.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.example.bookstore.dto.AddOrderRequest;
import org.example.bookstore.service.OrderService;

@Component
public class OrderListener {

    private final ObjectMapper objectMapper = new ObjectMapper();  // 使用 Jackson ObjectMapper

    @Autowired
    private OrderService orderService;  // 注入订单服务层

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;  // 注入 KafkaTemplate，用于发送处理结果

    private static final String ORDER_RESULT_TOPIC = "order_result_topic";  // 结果主题

    // 监听 order_topic 主题的消息
    @KafkaListener(topics = "order_topic", groupId = "order_group")
    public void listenOrderMessage(String message) {
        System.out.println("收到订单消息: " + message);

        // 将消息解析为 AddOrderRequest 对象
        AddOrderRequest request = parseMessage(message);  // 有合适的解析逻辑

        // 调用订单服务处理订单
        boolean success = orderService.saveOrder(
                request.getItems(),
                request.getUserId(),
                request.getName(),
                request.getAddress(),
                request.getPhone()
        );

        // 构建处理结果消息，包含 userId 和处理结果
        String resultMessage = request.getUserId() + ":" + (success ? "订单处理成功" : "订单处理失败，库存不足");

        // 将处理结果发送到 Kafka 的 order_result_topic 主题
        kafkaTemplate.send(ORDER_RESULT_TOPIC, resultMessage);
        System.out.println("订单处理结果已发送: " + resultMessage);
    }

    // 使用 Jackson 解析 JSON 字符串为 AddOrderRequest 对象
    private AddOrderRequest parseMessage(String message) {
        try {
            return objectMapper.readValue(message, AddOrderRequest.class);  // 将 JSON 转换为对象
        } catch (Exception e) {
            e.printStackTrace();  // 捕获并处理解析错误
            return null;  // 如果解析失败，返回 null 或抛出自定义异常
        }
    }
}
