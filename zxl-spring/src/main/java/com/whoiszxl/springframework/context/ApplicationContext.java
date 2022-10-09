package com.whoiszxl.springframework.context;

import com.whoiszxl.springframework.beans.factory.HierarchicalBeanFactory;
import com.whoiszxl.springframework.beans.factory.ListableBeanFactory;
import com.whoiszxl.springframework.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
