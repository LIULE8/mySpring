package org.myspring.factory.config.reader;

import org.myspring.factory.config.bean.BeanDefinition;
import org.myspring.factory.config.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry = new HashMap<>();
    private ResourceLoader resourceLoader;

    AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
