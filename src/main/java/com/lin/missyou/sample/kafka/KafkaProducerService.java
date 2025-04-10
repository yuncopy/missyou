package com.lin.missyou.sample.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate  = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic,  message)
                .addCallback(
                        result -> System.out.println(" 消息发送成功: " + message),
                        ex -> System.err.println(" 消息发送失败: " + message + ", 原因: " + ex.getMessage())
                );
    }

    // 发送带key的消息
    public void sendMessageWithKey(String topic, String key, String message) {
        kafkaTemplate.send(topic,  key, message);
    }
}