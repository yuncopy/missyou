package com.lin.missyou.sample.skill;

import com.lin.missyou.sample.skill.hero.Camille;
import com.lin.missyou.sample.skill.hero.Diana;
import com.lin.missyou.sample.skill.hero.Irelia;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeroConfiguration {

    public ISkill diana(){
        return new Diana();
    }

   // @Bean
    public ISkill irelia(){
        return new Irelia();
    }

    @Bean
    public ISkill camille(){
        return new Camille("lisi",10);
    }

}
