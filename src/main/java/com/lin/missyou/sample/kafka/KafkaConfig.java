package com.lin.missyou.sample.kafka;

import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.Arrays;

@Configuration
public class KafkaConfig {

    // 默认工厂（JSON反序列化）
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> defaultContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);  // 默认并发数
        return factory;
    }

    // 高优先级工厂（手动提交+批量消费）
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> highPriorityContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);  // 手动提交
        factory.setBatchListener(true);  // 支持批量消费
        factory.setConcurrency(5);  // 更高并发
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> delayAwareContainerFactory(ConsumerFactory<String, String> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        System.out.println("Processed:  " + factory);
        // 关键优化：按Topic设置不同并发度
        factory.setConcurrency(3);  // 默认并发数
        factory.setContainerCustomizer(container  -> {
            //String[] topics = container.getContainerProperties().getTopics();
            // 根据Topic重要性调整并发
            String[] topics = container.getAssignedPartitions()
                    .stream()
                    .map(TopicPartition::topic)
                    .distinct()
                    .toArray(String[]::new);

            // 根据Topic重要性调整并发
            if (Arrays.stream(topics).anyMatch(t  -> t.contains("immediate")))  {
                container.setConcurrency(5);  // 即时消息高并发
            }
        });

        return factory;
    }
}