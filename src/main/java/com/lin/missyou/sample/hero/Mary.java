package com.lin.missyou.sample.hero;

import org.springframework.stereotype.Component;

@Component
//@Lazy
public class Mary {
    public Mary() {
        System.out.println("Hello,Mary");
    }
    public void q(){
        System.out.println("Mary Q");
    }

    public void w(){
        System.out.println("Mary W");
    }

    public void e(){
        System.out.println("Mary E");
    }

    public void r(){
        System.out.println("Mary R");
    }

}
