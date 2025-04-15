package com.lin.missyou.sample.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TaskEventEnum {
    CREATE_TASK("CREATE_TASK", "创建"),
    EDIT_TASK("EDIT_TASK", "编辑"),
    EXECUTE_TASK("EXECUTE_TASK", "执行"),
    REGISTER_TASK("REGISTER_TASK", "注册"),
    NOT_EDIT("NOT_EDIT", "非法操作"),
    ;

    @Getter
    private final String code;

    @Getter
    private final String desc;

    public static TaskEventEnum getByCode(String code) {
        for (TaskEventEnum value : values()) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }
        return null;
    }

}