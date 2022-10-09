package com.whoiszxl.springframework.test.common;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.PropertyValue;
import com.whoiszxl.springframework.beans.PropertyValues;
import com.whoiszxl.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("loginService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("type", "微信登录"));
    }
}
