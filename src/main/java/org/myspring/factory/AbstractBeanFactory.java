package org.myspring.factory;

import com.google.common.base.Preconditions;
import org.myspring.factory.config.bean.BeanDefinition;
import org.myspring.factory.config.processor.BeanPostProcessor;
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

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>(256);

    /**
     * getBean，是真正创建bean的方法，但是源码中会做很多操作，如避免循环依赖等问题
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException(name);
        }
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name); // 这里加后置处理器，可以参与进spring的ioc创建中
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    /**
     * 真正生成对象的方法
     *
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (String beanName : this.beanDefinitionNames) {
            getBean(beanName);
        }
    }

    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {}

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * isAssignableFrom 和 instanceOf 作用类似，instanceOf:类， isAssignableFrom: 对象
     * @param type
     * @return
     * @throws Exception
     */
    public List<?> getBeansForType(Class<?> type) throws Exception {
        List<Object> beans = new ArrayList<>(256);
        for (String beanDefinitionName : beanDefinitionNames) {
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    /**
     * 从 beanDefinitionMap 中获取 bean的定义
     *
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
     *
     * @param beanName
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        Preconditions.checkNotNull(beanName, "'beanName' must not be empty");
        Preconditions.checkNotNull(beanDefinition, "BeanDefinition must not be null");
        beanDefinitionMap.put(beanName, beanDefinition);
        beanDefinitionNames.add(beanName);
    }
}
