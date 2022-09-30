package com.whoiszxl.springframework.test;

import com.whoiszxl.springframework.BeanFactory;
import com.whoiszxl.springframework.test.bean.LoginService;
import org.junit.Test;

public class SpringTest {

    @Test
    public void test_one() {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBeanDefinition("loginService", new LoginService());

        LoginService loginService = (LoginService) beanFactory.getBean("loginService");
        loginService.login("zxl", "123456");
    }
}
