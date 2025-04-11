package com.lin.missyou.sample.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayTopicConfig {

    private final KafkaProperties properties;

    public DelayTopicConfig(KafkaProperties properties) {
        this.properties  = properties;
    }

    @Bean
    public NewTopic immediateTopic() {
        return new NewTopic("delay-immediate", 3, (short) 1);
    }

    @Bean
    public NewTopic shortDelayTopic() {
        return new NewTopic("delay-10s", 3, (short) 1);
    }

    @Bean
    public NewTopic mediumDelayTopic() {
        return new NewTopic("delay-1m", 3, (short) 1);
    }

    @Bean
    public NewTopic longDelayTopic() {
        return new NewTopic("delay-1h", 3, (short) 1);
    }
}
