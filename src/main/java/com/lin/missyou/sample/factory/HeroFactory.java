package com.lin.missyou.sample.factory;

import com.lin.missyou.sample.factory.hero.Camille;
import com.lin.missyou.sample.factory.hero.Diana;
import com.lin.missyou.sample.factory.hero.Irelia;


public class HeroFactory {
    public static ISkill createHero(String name){
        // 用户只能输入字符串 所以有switch case 创建对象
        // 如果用户输入对象那就好了 这段代码就无需new对象了 这样就可以实现相对稳定
        ISkill hero = null;
        switch (name){
            case "irelia":
                hero = new Irelia();
                break;
            case "diana":
                hero = new Diana();
                break;
            case "camille":
                hero = new Camille();
                break;
            default:
                throw new RuntimeException("Invalid Hero Name");
        }
        return hero;
    }
}
