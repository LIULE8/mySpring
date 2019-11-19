package org.myspring.factory.config.reader;

import org.myspring.factory.config.BeanDefinition;
import org.myspring.factory.config.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry = new HashMap<>();

    private ResourceLoader resourceLoader;

    AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
