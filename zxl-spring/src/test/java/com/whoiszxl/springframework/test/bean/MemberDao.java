package com.whoiszxl.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {

    private static Map<String, String> memberTable = new HashMap<>();

    public void initDataMethod() {
        System.out.println("开始执行初始化操作");
        memberTable.put("zxl1", "123456");
        memberTable.put("zxl2", "123456");
        memberTable.put("zxl3", "123456");
        memberTable.put("zxl4", "123456");
        System.out.println("结束执行初始化操作");
    }

    public void destroyDataMethod() {
        System.out.println("执行销毁操作开始");
        memberTable.clear();
        System.out.println("执行销毁操作结束");
    }

    public Boolean queryMember(String username, String password) {
        for (String key : memberTable.keySet()) {
            String passwordTemp = memberTable.get(username);
            if(passwordTemp != null && passwordTemp.equals(password)) {
                return true;
            }
        }
        return false;
    }

}
