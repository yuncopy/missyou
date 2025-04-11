package com.lin.missyou.sample.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class UnifiedDelayConsumer {

    // 统一处理所有延迟级别
    @KafkaListener(
            topics = {
                    "${spring.kafka.topics.delay-levels.immediate}",
                    "${spring.kafka.topics.delay-levels.short_10s}",
                    "${spring.kafka.topics.delay-levels.medium_1m}",
                    "${spring.kafka.topics.delay-levels.long_1h}"
            },
            containerFactory = "defaultContainerFactory"
    )
    public void handleAllLevels(String message) {
        // 实际业务处理
        System.out.println("Processed:  " + message);

        // 可在此添加延迟补偿逻辑
        if (isCompensationNeeded(message)) {
            handleCompensation(message);
        }
    }

    @KafkaListener(topics = "${spring.kafka.topics.delay-levels.immediate}")
    public void consumeWithMetadata(String message,
                                    @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                    @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.printf(" 收到消息 [%s] from topic %s, partition %d, offset %d%n",
                message, topic, partition, offset);
    }

    private boolean isCompensationNeeded(String message) {
        // 检查消息是否因延迟过长需要补偿
        return message.contains("retry");
    }

    private void handleCompensation(String message) {
        // 补偿处理逻辑
    }

}