package com.whoiszxl.springframework.test.bean;

import com.whoiszxl.springframework.beans.factory.*;
import com.whoiszxl.springframework.context.ApplicationContext;
import com.whoiszxl.springframework.context.ApplicationContextAware;

public class LoginService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {


    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private IMemberDao memberDao;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName:::" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader:::" + classLoader);
    }

    private String code;

    private String type;

    private String result;

    public String login(String username, String password) {
        return memberDao.queryMember(username, password);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行LoginService销毁操作");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行LoginService afterPropertiesSet");
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
