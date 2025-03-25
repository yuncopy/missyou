package com.lin.missyou.sample.container;


// 示例类：用户仓库，依赖于DatabaseConnection
class UserRepository {
    private DatabaseConnection db;

    public UserRepository(DatabaseConnection db) {
        this.db  = db;
    }

    public void getUsers() {
        db.connect();
        System.out.println("Getting  users from the database");
    }
}