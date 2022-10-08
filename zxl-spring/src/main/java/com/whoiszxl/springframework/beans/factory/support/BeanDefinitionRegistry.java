package com.whoiszxl.springframework.beans.factory.support;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * 判断BeanDefinition是否存在
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);


    /**
     * 返回容器中所有bean的名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
