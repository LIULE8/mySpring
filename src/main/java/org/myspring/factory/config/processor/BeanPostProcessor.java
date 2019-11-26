package org.myspring.factory.config.processor;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public interface BeanPostProcessor {
	Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;
	Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}