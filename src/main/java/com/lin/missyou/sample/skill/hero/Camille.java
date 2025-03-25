package com.lin.missyou.sample.skill.hero;

import com.lin.missyou.sample.skill.ISkill;

//@Component
public class Camille implements ISkill {

    private String name;
    private Integer age;

    public Camille(String name, Integer age) {
        this.name = name;
        this.age = age;
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
        System.out.println("Hello,Camille : name :"+this.name +  " age :"+this.age);
    }
}
