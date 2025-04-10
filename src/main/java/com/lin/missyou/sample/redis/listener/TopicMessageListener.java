package com.lin.missyou.sample.redis.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageListener implements MessageListener {

    private static ApplicationEventPublisher publisher;


    @Autowired
    public void setPublisher(ApplicationEventPublisher publisher){

        TopicMessageListener.publisher = publisher;

    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel =message.getChannel();
        String expiredKey = new String(body);
        String topic = new String(channel);

        System.out.println("onMessage expiredKey:"+expiredKey); //pay:order:1
        System.out.println("onMessage topic:"+topic); //__keyevent@7__:expired

        TopicMessageListener.publisher.publishEvent(expiredKey); // 发布事件 -> 广播

    }
}
