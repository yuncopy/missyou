package com.lin.missyou.sample.database;

import com.lin.missyou.sample.IConnect;

public class MySQL implements IConnect {
    private String ip = "127.0.0.1";
    private Integer port = 3306;

    @Override
    public void connect(){
        System.out.println("IP地址:"+this.ip + " 端口:"+ this.port);
    }

    public MySQL() {}

    public MySQL(String ip,Integer port){
        this.ip = ip;
        this.port = port;
    }


}
