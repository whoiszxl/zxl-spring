package com.whoiszxl.springframework.test.event;

import com.whoiszxl.springframework.context.ApplicationListener;
import com.whoiszxl.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}