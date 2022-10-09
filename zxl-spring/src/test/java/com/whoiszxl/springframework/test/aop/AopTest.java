package com.whoiszxl.springframework.test.aop;

import com.whoiszxl.springframework.aop.AdvisedSupport;
import com.whoiszxl.springframework.aop.TargetSource;
import com.whoiszxl.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.whoiszxl.springframework.aop.framework.JdkDynamicAopProxy;
import com.whoiszxl.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.util.Arrays;

public class AopTest {


    @Test
    public void testAop() {
        IHelloService helloService = new HelloService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(helloService));
        advisedSupport.setMethodInterceptor(new HelloServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.whoiszxl.springframework.test.aop.IHelloService.*(..))"));

        IHelloService proxyJdk = (IHelloService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxyJdk.sayAll());


        System.out.println(proxyJdk.say("zxl"));
    }


    @Test
    public void testAop2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IHelloService helloService = applicationContext.getBean("helloService", IHelloService.class);

        String[] beanDefinitionNames = applicationContext.getBeanFactory().getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        System.out.println(helloService.sayAll());
    }
}
