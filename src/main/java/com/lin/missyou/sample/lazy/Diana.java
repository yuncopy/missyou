package com.lin.missyou.sample.lazy;


import org.springframework.stereotype.Component;

@Component
//@Lazy
public class Diana{

    public Diana()
    {
        System.out.println("Diana init");
    }
    public void q(){
        System.out.println("Diana Q");
    }

    public void w(){
        System.out.println("Diana W");
    }

    public void e(){
        System.out.println("Diana E");
    }

    public void r(){
        System.out.println("Diana R");
    }
}
