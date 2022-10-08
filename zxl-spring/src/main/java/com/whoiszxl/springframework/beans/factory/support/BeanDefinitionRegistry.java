package com.whoiszxl.springframework.beans.factory.support;

import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
