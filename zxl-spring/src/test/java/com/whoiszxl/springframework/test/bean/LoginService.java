package com.whoiszxl.springframework.test.bean;

public class LoginService {

    private String code;

    private MemberDao memberDao;

    public Boolean login(String username, String password) {
        return memberDao.queryMember(username, password);
    }

    @Override
    public String toString() {
        return "LoginService{" +
                "code='" + code + '\'' +
                '}';
    }
}
