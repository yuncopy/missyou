package com.lin.missyou.sample;

import com.lin.missyou.sample.database.MySQL;
import com.lin.missyou.sample.hero.Camille;
import com.lin.missyou.sample.hero.Diana;
import com.lin.missyou.sample.hero.Irelia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {
    @Value("${missyou.mysql.ip}") //读取配置文件
    private String ip;

    @Value("${missyou.mysql.port}")
    private Integer port;

    @Bean
    public IConnect mysql(){
        //自动配置
        return new MySQL(this.ip,this.port);
    }

}
