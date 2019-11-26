package org.myspring.factory.config.reader;

/**
 * 从配置中读取BeanDefinition
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
