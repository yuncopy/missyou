package com.lin.missyou.sample.extend;


import org.springframework.stereotype.Component;

@Component
public class Base {

    String name = "base";
    public void print(){
        System.out.println(getName());
    }

    public String getName() {
        return name;
    }
}
