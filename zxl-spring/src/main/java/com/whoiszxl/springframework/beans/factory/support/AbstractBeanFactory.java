package com.whoiszxl.springframework.beans.factory.support;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象bean工厂
 * 继承DefaultSingletonBeanRegistry，获取单例注册的能力
 * 实现BeanFactory，实现操作单例的代码，并在获取不到单例对象时，做抽象的获取与创建bean定义
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if(bean != null) {
            return (T)bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
