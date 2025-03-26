package com.lin.missyou.sample.automatic.conf;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class IreliaCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //return true; // true 注解可以加入   false 没有加入
        String name = conditionContext.getEnvironment().getProperty("hero.condition");
        return  "diana".equalsIgnoreCase(name); // true
    }
}
