package com.lin.missyou.sample.test;

public class A {

    private IC ic; //抽象类

    public void print (){
        System.out.println("class A");

        // C c = new C();
        // c.print(); //用的时候 主动实例化

        this.ic.print(); // 容器已主动注入 （有一种机制可以主动将对象注入到属性中）

        //理解：要依赖的类当成类本身的属性，从而不再进行实例化，通过把依赖的对象注入到属性中，然后通过属性调用
        //为什么有工厂模式还要引入依赖注入呢？
        //本身工厂模式将实例化的过程隔离，保持相对代码的稳定，但是本身依赖工厂类还是通过实例化工厂类进行获取类
        //只是巧用静态方法隐藏实例化的细节，这种方式还是不稳定，代码还是会发生变化

    }
}
