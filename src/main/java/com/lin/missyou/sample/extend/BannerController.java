package com.lin.missyou.sample.extend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private Sub sub;

    @GetMapping("/test")
    public void getByName(){
        sub.print();
        System.out.println("Sub getByName");
    }

}
