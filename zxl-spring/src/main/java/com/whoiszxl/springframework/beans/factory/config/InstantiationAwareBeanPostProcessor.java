package com.whoiszxl.springframework.beans.factory.config;

import com.whoiszxl.springframework.beans.BeansException;

public interface  InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

}
