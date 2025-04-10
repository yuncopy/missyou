package com.lin.missyou.sample.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/test")
    public void getByName(){
        System.out.println("BannerController getByName");

        String testMessage = "测试消息-" + System.currentTimeMillis();
        producerService.sendMessage("test-topic",  testMessage);

        // 等待消费者处理


        // 可以通过日志或断言验证消息是否被正确处理
    }

}
