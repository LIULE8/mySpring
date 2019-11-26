package org.myspring.factory.config.reader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.myspring.factory.config.bean.BeanDefinition;
import org.myspring.factory.config.bean.BeanReference;
import org.myspring.factory.config.bean.PropertyValue;
import org.myspring.factory.config.io.ResourceLoader;

import java.io.InputStream;
import java.util.List;

/**
 * 使用Dom4J操作xml的内容，并封装成beanDefinition，存进 registry<String, Definition>里
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        SAXReader saxReader = new SAXReader();
        Document doc = saxReader.read(inputStream);
        // 解析 bean
        registerBeanDefinitions(doc);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document doc) {
        Element root = doc.getRootElement();
        parseBeanDefinitions(root);
    }

    @SuppressWarnings("all")
    private void parseBeanDefinitions(Element root) {
        List elements = root.elements();
        for (Object element : elements) {
            if (element instanceof Element) {
                processBeanDefinition((Element) element);
            }
        }
    }

    private void processBeanDefinition(Element element) {
        String name = element.attributeValue("id");
        String className = element.attributeValue("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(element, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    @SuppressWarnings("all")
    private void processProperty(Element element, BeanDefinition beanDefinition) {
        List propertyNodes = element.elements("property");
        for (Object node : propertyNodes) {
            if (node instanceof Element) {
                continue;
            }
            Element property = ((Element) node);
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            if (value != null && value.length() > 0) {
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                continue;
            }
            String ref = property.attributeValue("ref");
            if (ref == null || ref.length() == 0) {
                throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                        + name + "' must specify a ref or value");
            }
            BeanReference beanReference = new BeanReference(ref);
            beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
        }
    }
}
