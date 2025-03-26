package com.lin.missyou.sample.automatic;


import com.lin.missyou.sample.automatic.hero.Diana;
import com.lin.missyou.sample.automatic.hero.Irelia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {

    @Bean
    @ConditionalOnProperty(value = "hero.condition",havingValue = "diana",matchIfMissing = true) //matchIfMissing默认值
    public ISkill diana(){
        return new Diana();
    }

    @Bean
    public ISkill irelia(){
        return new Irelia();
    }


}
