package com.lin.missyou.sample.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PullConsumerService implements CommandLineRunner {
    @Value("${rocketmq.consumer.name-server-addr}")
    private String nameServerAddr;
    @Value("${rocketmq.consumer.group-name}")
    private String groupName;


    public void messageListener() throws  MQClientException {

        // 初始化consumer，并设置consumer group name
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);

        // 设置NameServer地址
        consumer.setNamesrvAddr(nameServerAddr);
        //订阅一个或多个topic，并指定tag过滤条件，这里指定*表示接收所有tag的消息
        consumer.subscribe("TopicTest", "*");
        //注册回调接口来处理从Broker中收到的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override //定义没有名字的具体实现
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt messageExt : msgs){
                   System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), messageExt);
                    String body = new String(messageExt.getBody(), StandardCharsets.UTF_8); // 将字节消息转换为字符串
                    System.out.printf("Received message: %s%n", body); // 处理或解析字符串数据
               }
                // 返回消息消费状态，ConsumeConcurrentlyStatus.CONSUME_SUCCESS为消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

//            consumer.registerMessageListener((MessageListenerConcurrently)(messageExtList,context)->{
//                for (MessageExt messageExt : messageExtList){
//                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), messageExt);
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            });

        // 启动Consumer
        consumer.start();
        System.out.printf("Consumer Started.%n");

    }
    @Override
    public void run(String... args) throws Exception {
        this.messageListener();
    }

}
