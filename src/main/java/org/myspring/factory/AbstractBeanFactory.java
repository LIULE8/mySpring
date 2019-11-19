package org.myspring.factory;

import com.google.common.base.Preconditions;
import org.myspring.factory.config.BeanDefinition;
import org.myspring.factory.exception.NoSuchBeanDefinitionException;
import org.myspring.factory.interfaces.BeanDefinitionRegistry;
import org.myspring.factory.interfaces.BeanFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public class AbstractBeanFactory implements BeanFactory, BeanDefinitionRegistry {

  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

  private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

  @Override
  public Object getBean(String name) {
    return null;
  }

    /**
     * 从 beanDefinitionMap 中获取 bean的定义
     * @param beanName
     * @return
     */
  @Override
  public BeanDefinition getBeanDefinition(String beanName) {
    BeanDefinition bd = this.beanDefinitionMap.get(beanName);
    if (bd == null) {
      throw new NoSuchBeanDefinitionException(beanName);
    }
    return bd;
  }

    /**
     * 注册 beanDefinition 到 map里面
     * @param beanName
     * @param beanDefinition
     */
  @Override
  public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
      Preconditions.checkNotNull(beanName, "'beanName' must not be empty");
      Preconditions.checkNotNull(beanDefinition, "BeanDefinition must not be null");
      beanDefinitionMap.put(beanName, beanDefinition);
  }
}
