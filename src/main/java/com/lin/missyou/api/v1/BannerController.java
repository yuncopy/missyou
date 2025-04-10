package com.lin.missyou.api.v1;

import com.lin.missyou.exception.http.NotFoundException;
import com.lin.missyou.model.Banner;
import com.lin.missyou.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/banner")
@Validated
//@Lazy
public class BannerController {


    @Autowired
    private BannerService bannerService;



    @GetMapping("/name/{name}")
    public Banner getByName(@PathVariable String name){
        Banner banner = bannerService.getByName(name);
        if(null == banner){
            throw new NotFoundException(30005);
        }

        return banner;
    }

    /*set方法注入
    @Autowired
    public void setMary(Mary mary) {
        this.mary = mary;
    }
     */

   /*构造函数注入
    @Autowired
    public BannerController(Mary mary){
        //构造函数注入
        this.mary = mary; // 定义属性使用构造函数注入
    }
     */

    @GetMapping("/test")
    public String getTest() throws Exception{
        throw  new Exception("这里出错啦");
       // return "你好 集润";
    }

    @GetMapping("/mysql")
    public String getMsql() {
        return "你好 MySQL";
    }

    @GetMapping("/mysql1")
    public void getMsql(HttpServletResponse response) throws IOException {
        response.getWriter().write("你好 MySQL1"); //编码
        //使用使用return 不操作原生的HttpServletResponse
    }



//    @PostMapping(value = "/test/{id1}")
//    public PersonDTO test(@PathVariable(name="id1") @Range(min = 1,max = 10,message = "不能超过10噢") Integer id,
//                          @RequestParam(name="name2") @Length(min = 3) String name,
//                          @RequestBody @Validated PersonDTO personDTO){
//        iSkill.r();
//        PersonDTO dto = new PersonDTO();
//        dto.setName("雪");
//        dto.setAge(18);
////        PersonDTO dto = PersonDTO.builder()
////                .name("")
////                .age(18)
////                .build();
//        return dto;
//    }


}
