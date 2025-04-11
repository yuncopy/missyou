package com.lin.missyou.sample.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class DelayRouter {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DelayRouter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 延迟级别定义
    private enum DelayLevel {
        IMMEDIATE(0, "delay-immediate"),
        SHORT(10, "delay-10s"),
        MEDIUM(60, "delay-1m"),
        LONG(3600, "delay-1h");

        private final int seconds;
        private final String topic;

        DelayLevel(int seconds, String topic) {
            this.seconds  = seconds;
            this.topic  = topic;
        }
    }

    public void sendWithDelay(String message, int delaySeconds) {
        DelayLevel level = resolveLevel(delaySeconds);
        kafkaTemplate.send(level.topic,  message)
                .addCallback(
                        result -> System.out.println(" 消息发送成功: " + message + " / topic:"+level.topic),
                        ex -> System.err.println(" 消息发送失败: " + message + ", 原因: " + ex.getMessage())
                );

        // 长延迟消息的降级处理
        if (level == DelayLevel.LONG) {
            scheduleDowngrade(message, delaySeconds - 60); // 提前1分钟降级
        }
    }

    private DelayLevel resolveLevel(int delaySeconds) {
        if (delaySeconds <= 0) return DelayLevel.IMMEDIATE;
        if (delaySeconds <= 10) return DelayLevel.SHORT;
        if (delaySeconds <= 60) return DelayLevel.MEDIUM;
        return DelayLevel.LONG;
    }

    @Async
    protected void scheduleDowngrade(String message, long remainingSeconds) {
        try {
            TimeUnit.SECONDS.sleep(remainingSeconds);
            kafkaTemplate.send("delay-1m",  message); // 降级到分钟级
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}