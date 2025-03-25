package com.lin.missyou.sample.container;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// 简单的IoC容器类
class Container {
    private Map<Class<?>, Supplier<?>> bindings = new HashMap<>();

    // 绑定接口或抽象类到具体实现
    public <T> void bind(Class<T> abstractClass, Supplier<? extends T> concreteSupplier) {
        bindings.put(abstractClass,  concreteSupplier);
    }

    // 解析并返回实例
    public <T> T resolve(Class<T> abstractClass) {
        Supplier<?> supplier = bindings.get(abstractClass);
        if (supplier == null) {
            throw new RuntimeException("No binding found for " + abstractClass.getName());
        }
        return abstractClass.cast(supplier.get());
    }
}




