package org.myspring.factory.interfaces;

import org.myspring.factory.config.bean.BeanDefinition;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);
}
