package com.whoiszxl.springframework.beans.factory.support;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.core.io.Resource;
import com.whoiszxl.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource...  resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
