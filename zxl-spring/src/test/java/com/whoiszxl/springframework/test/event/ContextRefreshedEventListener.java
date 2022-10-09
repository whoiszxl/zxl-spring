package com.whoiszxl.springframework.test.event;

import com.whoiszxl.springframework.context.ApplicationListener;
import com.whoiszxl.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}