package com.whoiszxl.springframework.aop;

public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
