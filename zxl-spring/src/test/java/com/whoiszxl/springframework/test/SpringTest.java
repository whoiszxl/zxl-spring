package com.whoiszxl.springframework.test;

import com.whoiszxl.springframework.aop.aspectj.AspectJExpressionPointcut;
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
import com.whoiszxl.springframework.test.event.CustomEvent;
import org.junit.Before;
import org.junit.Test;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.lang.reflect.Method;

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
        String flag = loginService.login("zxl2", "123456");
        System.out.println(flag);
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

        //beanFactory.registerBeanDefinition("memberDao", new BeanDefinition(MemberDao.class));


        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("code", "zxl-code"));
        propertyValues.addPropertyValue(new PropertyValue("memberDao", new BeanReference("memberDao")));

        BeanDefinition beanDefinition = new BeanDefinition(LoginService.class, propertyValues);
        beanFactory.registerBeanDefinition("loginService", beanDefinition);

        LoginService loginService = (LoginService) beanFactory.getBean("loginService");
    }



    @Test
    public void testXml2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        LoginService loginService = applicationContext.getBean("loginService", LoginService.class);
        loginService.login("zxl1", "123456");

    }

    @Test
    public void testXml3() {
        ClassPathXmlApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        LoginService loginService1 = applicationContext.getBean("loginService", LoginService.class);
        LoginService loginService2 = applicationContext.getBean("loginService", LoginService.class);

        System.out.println(loginService1);
        System.out.println(loginService2);

        String result = loginService1.login("zxl1", "123456");
        System.out.println(result);

    }


    @Test
    public void testEvent() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1000L, "eureka!!"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void aopTest() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.whoiszxl.springframework.test.bean.LoginService.*(..))");
        Class<LoginService> clazz = LoginService.class;

        Method method = clazz.getDeclaredMethod("say");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }
}
