package com.whoiszxl.springframework.beans.factory.config;

import com.whoiszxl.springframework.beans.BeansException;

public interface BeanPostProcessor {

    /**
     * 在bean对象执行初始化方法之前执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean对象执行初始化方法之后执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
