package com.lin.missyou.sample.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    //@KafkaListener(topics = "test-topic", groupId = "test-group")
    public void consume(String message) {
        System.out.println(" 接收到消息: " + message);
    }

    // 监听多个topic
    //@KafkaListener(topics = {"test-topic", "another-topic"})
    public void consumeMultipleTopics(String message) {
        System.out.println(" 从多个topic接收到消息: " + message);
    }

    // 获取消息元数据
    @KafkaListener(topics = "test-topic")
    public void consumeWithMetadata(String message,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                    @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.printf(" 收到消息 [%s] from topic %s, partition %d, offset %d%n",
                message, topic, partition, offset);
    }
}