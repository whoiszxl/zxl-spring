package com.whoiszxl.springframework.beans.factory;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
