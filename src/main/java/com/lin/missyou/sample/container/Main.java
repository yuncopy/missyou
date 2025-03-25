package com.lin.missyou.sample.container;

// 主类
public class Main {
    public static void main(String[] args) {
        // 创建容器
        Container container = new Container();

        // 绑定DatabaseConnection到具体实现
        container.bind(DatabaseConnection.class,  DatabaseConnection::new);

        // 绑定UserRepository到具体实现，并注入DatabaseConnection依赖
        container.bind(UserRepository.class,  () -> new UserRepository(container.resolve(DatabaseConnection.class)));

        // 解析并获取UserRepository实例
        UserRepository userRepository = container.resolve(UserRepository.class);

        // 使用实例
        userRepository.getUsers();  // 输出: Connected to the database\nGetting users from the database
    }
}