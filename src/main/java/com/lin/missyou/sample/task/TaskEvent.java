package com.lin.missyou.sample.task;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskEvent {
    TaskEventEnum type();

    TaskEventEnum edit() default TaskEventEnum.NOT_EDIT;
}
