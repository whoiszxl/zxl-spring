package com.whoiszxl.springframework.test;

import com.whoiszxl.springframework.beans.PropertyValue;
import com.whoiszxl.springframework.beans.PropertyValues;
import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.config.BeanReference;
import com.whoiszxl.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.whoiszxl.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.whoiszxl.springframework.context.support.ClassPathXmlApplicationContext;
import com.whoiszxl.springframework.core.io.DefaultResourceLoader;
import com.whoiszxl.springframework.core.io.Resource;
import com.whoiszxl.springframework.test.bean.LoginService;
import com.whoiszxl.springframework.test.bean.MemberDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class SpringTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testXml() throws IOException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        LoginService loginService = beanFactory.getBean("loginService", LoginService.class);
        Boolean flag = loginService.login("zxl2", "123456");
        if(flag) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }

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



    @Test
    public void testXml2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        LoginService loginService = applicationContext.getBean("loginService", LoginService.class);
        Boolean result = loginService.login("zxl1", "123456");
        MemberDao memberDao = loginService.getApplicationContext().getBean("memberDao", MemberDao.class);
        System.out.println("memberDao:::" + memberDao);
        System.out.println(result);
    }
}
