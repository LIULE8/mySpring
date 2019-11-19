package org.myspring.factory.exception;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public class NoSuchBeanDefinitionException extends RuntimeException {

  public NoSuchBeanDefinitionException(String beanName) {
    super("No bean named '" + beanName + "' available");
  }
}
