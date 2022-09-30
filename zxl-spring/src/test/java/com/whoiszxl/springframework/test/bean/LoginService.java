package com.whoiszxl.springframework.test.bean;

public class LoginService {

    public String login(String username, String password) {
        System.out.println("登录成功 username:" + username + "password:" + password);
        return "token:123456";
    }
}
