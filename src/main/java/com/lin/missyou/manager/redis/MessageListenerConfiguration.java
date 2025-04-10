package com.lin.missyou.manager.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;

@Configuration
public class MessageListenerConfiguration {

    @Value("${spring.redis.listen-pattern}")
    public String pattern;

    @Autowired
    private TopicMessageListener topicMessageListener;

    //将listener注入到容器中，防止调用被管理的bean出现空指针
//    @Bean
//    public TopicMessageListener getTopicListener(){
//        return new TopicMessageListener();
//    }
    //用于消息监听，需要将 Topic和MessageListener 注册到 RedisMessageListenerContainer 中。
    //当 Topic 上有消息时，由 RedisMessageListenerContainer 通知 MessageListener，
    // MessageListener 通过 onMessage 方法拿到消息后，自行处理。
    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnection){
        //container负责连接redis服务器 并且将之前写的listener绑定到监听里
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnection);	//redisConnection取到redis的连接信息
        Topic topic = new PatternTopic(pattern);    //监听主题初始化
        container.addMessageListener(topicMessageListener,topic); //添加监听器和所监听得主题
       // container.addMessageListener(getTopicListener(), topic);
        return container;
    }
}
