package com.lin.missyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
        basePackages = "com.lin.missyou", // 指定扫描的基础包
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX, // 使用正则表达式匹配
                pattern = "com\\.lin\\.missyou\\.sample\\..*" // 排除的包路径
        )
)
public class MissyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissyouApplication.class, args);
    }

}
