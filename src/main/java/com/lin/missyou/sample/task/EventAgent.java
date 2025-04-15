package com.lin.missyou.sample.task;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EventAgent {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @AfterReturning(pointcut = "@annotation(taskEvent)", returning = "taskCenterModel")
    public void around(TaskEvent taskEvent, TaskCenterModel taskCenterModel) {
        System.out.println("TaskController getByName");
        applicationEventPublisher.publishEvent(taskCenterModel);
    }
}
