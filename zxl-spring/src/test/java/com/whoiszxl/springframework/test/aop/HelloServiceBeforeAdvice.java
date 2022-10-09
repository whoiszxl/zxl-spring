package com.whoiszxl.springframework.test.aop;

import com.whoiszxl.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class HelloServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
