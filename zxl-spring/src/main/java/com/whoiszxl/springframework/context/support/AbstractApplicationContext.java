package com.whoiszxl.springframework.context.support;

import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.whoiszxl.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.whoiszxl.springframework.beans.factory.config.BeanPostProcessor;
import com.whoiszxl.springframework.context.ApplicationEvent;
import com.whoiszxl.springframework.context.ApplicationListener;
import com.whoiszxl.springframework.context.ConfigurableApplicationContext;
import com.whoiszxl.springframework.context.event.ApplicationEventMulticaster;
import com.whoiszxl.springframework.context.event.ContextClosedEvent;
import com.whoiszxl.springframework.context.event.ContextRefreshedEvent;
import com.whoiszxl.springframework.context.event.SimpleApplicationEventMulticaster;
import com.whoiszxl.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        //1. 创建beanFactory，并加载BeanDefinition
        refreshBeanFactory();

        //2. 获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //3. 在bean实例化之前，执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //4. 注册BeanPostProcessor，因为此类需要提前于其他bean对象实例化之前注册
        registerBeanPostProcessors(beanFactory);

        //5. 提前实例化单例bean独享
        beanFactory.preInstantiateSingletons();

        //6. 初始化事件发布者
        initApplicationEventMulticaster();

        //7. 注册事件监听器
        registerListeners();

        //8. 发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }



    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }
}
