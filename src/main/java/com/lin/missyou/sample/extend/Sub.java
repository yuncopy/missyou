package com.lin.missyou.sample.extend;


import org.springframework.stereotype.Component;

@Component
public class Sub extends Base {

    String name = "sub";

    @Override
    public String getName() {
        return name;
    }
}
