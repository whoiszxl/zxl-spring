package com.whoiszxl.springframework.context.event;

import com.whoiszxl.springframework.context.ApplicationEvent;
import com.whoiszxl.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
