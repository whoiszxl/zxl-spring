package com.whoiszxl.springframework.beans.factory;

import com.whoiszxl.springframework.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回bean的实例列表
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取容器中所有的bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
