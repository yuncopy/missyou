package com.lin.missyou.sample.condition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {


    @Autowired
    private ISkill iSkill;

    @GetMapping("/test")
    public void getByName(){
        iSkill.r();
        System.out.println("iSkill getByName");
    }

}
