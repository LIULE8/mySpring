package org.myspring.factory.interfaces;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public interface BeanFactory {
  Object getBean(String name) throws Exception;
}
