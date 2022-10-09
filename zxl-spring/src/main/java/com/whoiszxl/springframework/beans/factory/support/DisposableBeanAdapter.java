package com.whoiszxl.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.whoiszxl.springframework.beans.BeansException;
import com.whoiszxl.springframework.beans.factory.DisposableBean;
import com.whoiszxl.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if(StrUtil.isNotBlank(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(destroyMethod == null) {
                throw new BeansException("没有找到销毁方法: " + destroyMethodName);
            }

            destroyMethod.invoke(bean);
        }
    }
}
