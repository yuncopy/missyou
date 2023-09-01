package com.lin.missyou.manager.redis;

import com.lin.missyou.bo.OrderMessageBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageListener implements MessageListener {

    private static ApplicationEventPublisher publisher;

   // @Autowired
   // private CouponBackService couponBackService;

   // @Autowired
   // private OrderCancelService orderCancelService;

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
        OrderMessageBO messageBO = new OrderMessageBO(expiredKey);
        TopicMessageListener.publisher.publishEvent(messageBO);

        //couponBackService.returnBack(messageBO);
        //orderCancelService.cancel(messageBO);
    }
}
