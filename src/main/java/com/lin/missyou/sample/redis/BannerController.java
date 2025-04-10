package com.lin.missyou.sample.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public void getByName(){
        System.out.println("BannerController getByName");

        String key = "pay:order:1"; //检查读是否同一个redis服务  访问mac本地服务的redis
        long payTimeLimit = 10L;
        stringRedisTemplate.opsForValue().set(key,"1",payTimeLimit, TimeUnit.SECONDS);

        String value = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.opsForValue().set("name","lisi");
        System.out.println(value);
    }

}
