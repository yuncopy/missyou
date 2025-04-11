package com.lin.missyou.sample.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private SyncProducerService syncProducerService;

    @GetMapping("/test")
    public void getByName() throws Exception{
        System.out.println("BannerController getByName");
        syncProducerService.sendMessage("TopicTest", "TagA", "Hello RocketMQ");
    }

}
