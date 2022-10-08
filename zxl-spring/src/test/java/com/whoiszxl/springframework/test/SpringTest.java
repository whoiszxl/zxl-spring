package com.whoiszxl.springframework.test;

import com.whoiszxl.springframework.beans.PropertyValue;
import com.whoiszxl.springframework.beans.PropertyValues;
import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.config.BeanReference;
import com.whoiszxl.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.whoiszxl.springframework.test.bean.LoginService;
import com.whoiszxl.springframework.test.bean.MemberDao;
import org.junit.Test;

public class SpringTest {

    @Test
    public void test_one() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("loginService", new BeanDefinition(LoginService.class));

        LoginService loginService = (LoginService) beanFactory.getBean("loginService", "10086");
        loginService.login("zxl1", "123456");

        LoginService loginService2 = (LoginService) beanFactory.getBean("loginService", "10086");
        loginService2.login("zxl2", "123456");
    }

    @Test
    public void test_two() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("memberDao", new BeanDefinition(MemberDao.class));


        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("code", "zxl-code"));
        propertyValues.addPropertyValue(new PropertyValue("memberDao", new BeanReference("memberDao")));

        BeanDefinition beanDefinition = new BeanDefinition(LoginService.class, propertyValues);
        beanFactory.registerBeanDefinition("loginService", beanDefinition);

        LoginService loginService = (LoginService) beanFactory.getBean("loginService");
        if(loginService.login("zxl1", "123456")) {
            System.out.println("登录成功");
        }else {
            System.out.println("登陆失败");
        }
    }
}
