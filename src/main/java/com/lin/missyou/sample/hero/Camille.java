package com.lin.missyou.sample.hero;

import com.lin.missyou.sample.ISkill;


public class Camille implements ISkill {
    private String name; //特征
    private Integer age; //特征
    //有参构造函数
    public Camille(String name ,Integer age) {
        this.name = name;
        this.age = age;
        System.out.println(this.name+"Hello,Camille"+this.age);
    }
    public Camille() {
        System.out.println("Hello,Camille");
    }

    public void q(){
    System.out.println("Camille Q");
}

    public void w(){
    System.out.println("Camille W");
}

    public void e(){
    System.out.println("Camille E");
}

    public void r(){
    System.out.println("Camille R");
}
}
