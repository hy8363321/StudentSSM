package com.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Test {
    private Long id;

    private String username;

    private String password;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date time;

    public Test(){

    }
    public Test( String username, String password, Date time) {
        this.username = username;
        this.password = password;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}