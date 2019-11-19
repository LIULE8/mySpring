package org.myspring.factory.config.bean;

/**
 * 源码里BeanDefinition是接口定义一些操作，实体是RootDefinition等，为了简便直接用BeanDefinition
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public class BeanDefinition {

  private Object bean;

  private Class<?> beanClass;

  private String beanClassName;

  private PropertyValues propertyValues = new PropertyValues();

  //等其他属性省略，如lazy, dependsOn, scope

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(Class<?> beanClass) {
    this.beanClass = beanClass;
  }

  public String getBeanClassName() {
    return beanClassName;
  }

  public void setBeanClassName(String beanClassName) {
    this.beanClassName = beanClassName;
    try {
      this.beanClass = Class.forName(beanClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }
}
