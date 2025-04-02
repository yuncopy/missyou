package com.lin.missyou.optional;


        import lombok.Getter;
        import lombok.Setter;
        import org.junit.Test;

        import java.util.Optional;

public class OptionalTest {
    @Test
    public void testOptional(){
        /**构建Optional * Optional 目的： 简化代码 （标准化） / 强制判空（编译时暴露NPE错误） => 编译时标准化处理NPE错误*/
        //构建空的Optional
//        Optional<String> empty = Optional.empty();
//        empty.get();
//        //构建Optional传入空值会报错
        //Optional<String> t2 = Optional.of(null);
       //Optional<String> t2 = Optional.ofNullable(null);
        //String s1 = t2.get(); //编译时取值时直接报错(空指针)
        //构建Optional允许传入空值
        Optional<String> t2 = Optional.ofNullable("a");
        //t2.get();
//        String s = t2.get(); //编译时取值时直接报错(空指针)


//        t2不为空时执行ifPresent()中的语句
      //  t2.ifPresent(t-> System.out.println(t));  //lambada表达式写法 Consumer 消费者 处理值 t
//        t2.ifPresent(System.out::println);          //方法引用写法
//
        //传统写法
//        String s = "默认值";
//        if(null != t2){
//            s = t2.get();
//        }else{
//
//        }
//        //应用Optional的写法
//        String s = t2.orElse("默认值"); //orElseThrow
//        System.out.println(s);


//        //Optional链式操作 .map()返回值会再次包装成Optional类
        //String s = t2.map(t->t+"b").orElse("c");
        //System.out.println(s);
        t2.map(t->t+"b").ifPresent(System.out::println);
        //Consumer 消费者 处理值   /  Supplier 提供者 返回值
        //在Java中，Consumer、Supplier、Runnable和Function是常用的函数式接口，它们分别用于不同的场景。

        User user = new User("小红","12345");
        System.out.println("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
    }

    private User createNewUser(){
        System.out.println("createNewUser");
        return new User("小明","123456");
    }
}

@Getter
@Setter
class User{

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
