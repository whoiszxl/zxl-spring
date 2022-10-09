package com.whoiszxl.springframework.beans.factory;

public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<T> getObjectType();

    boolean isSingleton();
}
