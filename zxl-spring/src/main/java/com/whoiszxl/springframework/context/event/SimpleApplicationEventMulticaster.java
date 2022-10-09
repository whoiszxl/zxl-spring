package com.whoiszxl.springframework.context.event;

import com.whoiszxl.springframework.beans.factory.BeanFactory;
import com.whoiszxl.springframework.context.ApplicationEvent;
import com.whoiszxl.springframework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (ApplicationListener applicationListener : getApplicationListeners(event)) {
            applicationListener.onApplicationEvent(event);
        }
    }
}
