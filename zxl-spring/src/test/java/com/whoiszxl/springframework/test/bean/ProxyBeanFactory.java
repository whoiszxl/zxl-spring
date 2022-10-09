package com.whoiszxl.springframework.test.bean;

import com.whoiszxl.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyBeanFactory implements FactoryBean<IMemberDao> {

    @Override
    public IMemberDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> memberTable = new HashMap<>();
            memberTable.put("zxl1", "123456");
            memberTable.put("zxl2", "123456");
            memberTable.put("zxl3", "123456");
            memberTable.put("zxl4", "123456");

            return "代理了 " + method.getName() + "::" + memberTable.get(args[0].toString());
        };
        return (IMemberDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{IMemberDao.class},
                handler);
    }

    @Override
    public Class<IMemberDao> getObjectType() {
        return IMemberDao.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
