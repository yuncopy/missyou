package com.lin.missyou.sample.automatic;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

//@ComponentScan // 第一种 导入IOC容器
//@Import(HeroConfiguration.class) //第二种 导入IOC容器
//@Import(LOLConfigurationSelector.class) // 第三种 导入IOC容器
@EnableLOLConfiguration //第四种 导入IOC容器
public class LOLApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(LOLApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);
        ISkill iSkill = (ISkill) context.getBean("irelia");
        iSkill.r();
    }
}
