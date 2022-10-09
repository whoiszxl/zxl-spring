package com.whoiszxl.springframework.test.common;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.config.BeanPostProcessor;
import com.whoiszxl.springframework.test.bean.LoginService;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("loginService".equals(beanName)) {
            LoginService loginService = (LoginService) bean;
            loginService.setResult("微信登录成功");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
