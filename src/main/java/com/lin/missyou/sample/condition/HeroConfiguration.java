package com.lin.missyou.sample.condition;


import com.lin.missyou.sample.condition.hero.Diana;
import com.lin.missyou.sample.condition.hero.Irelia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {

    @Bean
    //@Conditional(DianaCondition.class) // 自定义条件注解     @ConditionalOnProperty 内置条件注解
    @ConditionalOnProperty(value = "hero.condition",havingValue = "diana",matchIfMissing = true) //matchIfMissing默认值
    public ISkill diana(){
        return new Diana();
    }

    @Bean
    public ISkill irelia(){
        return new Irelia();
    }


}
