package com.whoiszxl.springframework.beans.factory.support;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.beans.factory.FactoryBean;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;
import com.whoiszxl.springframework.beans.factory.config.BeanPostProcessor;
import com.whoiszxl.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.whoiszxl.springframework.utils.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象bean工厂
 * 继承DefaultSingletonBeanRegistry，获取单例注册的能力
 * 实现BeanFactory，实现操作单例的代码，并在获取不到单例对象时，做抽象的获取与创建bean定义
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();


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
        Object sharedInstance = getSingleton(name);
        if(sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if(!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if(object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }


    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
