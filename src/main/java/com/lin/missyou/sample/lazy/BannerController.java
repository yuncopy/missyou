package com.lin.missyou.sample.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
//@Lazy
public class BannerController {
//    private final ApplicationContext context;
//
//    public BannerController(ApplicationContext context){
//        System.out.println("BannerController init");
//        this.context  = context;
//    }
    @Autowired
    private Diana diana;


    @GetMapping("/test")
    public void getByName(){
        this.diana.r();
        //Diana diana = this.context.getBean(Diana.class);
        //diana.r();
        System.out.println("Diana getByName");
    }

}
