package com.whoiszxl.springframework.beans.factory;

import com.whoiszxl.springframework.beans.BeansException;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
