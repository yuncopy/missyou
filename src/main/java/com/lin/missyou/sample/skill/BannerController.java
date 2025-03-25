package com.lin.missyou.sample.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    //在Spring框架中，@Autowired 注解默认是通过 byType 的方式注入依赖，但也可以通过配置实现 byName 的方式注入。
    // @Primary 或 @Qualifier

    //@Autowired
    //private ISkill diana;

    //@Autowired
    //@Qualifier("irelia") //指定类
    //private ISkill iSkill; //定义接口

    @Autowired
    private ISkill camille; // @Configuration + @Bean

    @GetMapping("/test")
    public void getByName(){
        //irelia.r();
        //iSkill.r();
        camille.r();
        System.out.println("Diana getByName");
    }

}
