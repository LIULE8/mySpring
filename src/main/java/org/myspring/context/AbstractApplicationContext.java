package org.myspring.context;

import org.myspring.factory.AbstractBeanFactory;
import org.myspring.factory.config.processor.BeanPostProcessor;

import java.util.List;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    /**
     * ioc核心方法，实际上会操作12个核心方法，这里简化版
     *
     * @throws Exception
     */
    protected void refresh() throws Exception {
        loadBeanDefinitions(beanFactory); //这个调用不在refresh方法里，为了方便放在这里.
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    private void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
