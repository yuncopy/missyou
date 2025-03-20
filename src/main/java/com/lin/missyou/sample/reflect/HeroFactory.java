package com.lin.missyou.sample.reflect;

public class HeroFactory {
    public static ISkill createHero(String name) throws Exception{

        //反射机制消除所有的变化
        // 将用户的输入字符串 转换成对象 消除对象的实例化
        String classStr = "reflect.hero."+name;
        Class<?> cla = Class.forName(classStr); //元类
        Object obj= cla.newInstance();
        //clazz.getDeclaredConstructor().newInstance()
        return (ISkill)obj;
    }
}
