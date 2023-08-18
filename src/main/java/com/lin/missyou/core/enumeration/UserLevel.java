package com.lin.missyou.core.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UserLevel {

    TRUE(1, "是"),
    FALSE(2, "否"),
    ;

    @Getter
    private final Integer code;

    @Getter
    private final String desc;

}
