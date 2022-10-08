package com.whoiszxl.springframework.beans.factory.config;

/**
 * 单例bean注册器接口
 */
public interface SingletonBeanRegistry {

    /**
     * 通过类名称获取单例对象
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
