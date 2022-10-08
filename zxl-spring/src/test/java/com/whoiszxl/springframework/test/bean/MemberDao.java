package com.whoiszxl.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {

    private static Map<String, String> memberTable = new HashMap<>();

    static {
        memberTable.put("zxl1", "123456");
        memberTable.put("zxl2", "123456");
        memberTable.put("zxl3", "123456");
        memberTable.put("zxl4", "123456");
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
