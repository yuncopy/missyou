package com.lin.missyou.sample.hero;

import com.lin.missyou.sample.ISkill;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Diana implements ISkill {

    //无参数构造函数
    public Diana() { System.out.println("Hello,Diana"); }

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
