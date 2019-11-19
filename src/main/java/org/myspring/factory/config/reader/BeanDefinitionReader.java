package org.myspring.factory.config.reader;

/**
 * 从配置中读取BeanDefinition
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
