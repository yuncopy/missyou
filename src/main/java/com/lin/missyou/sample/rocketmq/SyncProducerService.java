package com.lin.missyou.sample.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SyncProducerService {

    @Value("${rocketmq.producer.name-server-addr}")
    private String nameServerAddr;
    @Value("${rocketmq.producer.group-name}")
    private String groupName;
    private DefaultMQProducer producer;
    @PostConstruct //理解使用的背景
    public void defaultMQProducer (){
        this.producer = new DefaultMQProducer(groupName);
        this.producer.setNamesrvAddr(nameServerAddr);
        try {
            this.producer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
    }

    public String sendMessage(String topic,String tag,String message){
        try {
            Message msg = new Message(topic /* Topic */,
                    tag /* Tag */,
                    message.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );   //（3）
            msg.setDelayTimeLevel(3);
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = producer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
            return sendResult.getMsgId();
        }catch(Exception e){
            e.printStackTrace();
        }
        // 一旦producer不再使用，关闭producer
        //producer.shutdown();
        return null;
    }

}
