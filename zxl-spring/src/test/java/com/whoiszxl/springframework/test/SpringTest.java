package com.whoiszxl.springframework.test;

import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.whoiszxl.springframework.test.bean.LoginService;
import org.junit.Test;

public class SpringTest {

    @Test
    public void test_one() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("loginService", new BeanDefinition(LoginService.class));

        LoginService loginService = (LoginService) beanFactory.getBean("loginService");
        loginService.login("zxl1", "123456");


        LoginService loginService2 = (LoginService) beanFactory.getBean("loginService");
        loginService2.login("zxl2", "123456");
    }
}
