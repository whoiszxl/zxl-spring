package com.whoiszxl.springframework.beans.factory.config;

/**
 * Bean定义包装类
 */
public class BeanDefinition {

    /**
     * 将对象改变为class类，将实例化操作放到容器中进行处理
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
