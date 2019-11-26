package org.myspring.context.xml;

import org.myspring.context.AbstractApplicationContext;
import org.myspring.factory.AbstractBeanFactory;
import org.myspring.factory.DefaultListableBeanFactory;
import org.myspring.factory.config.bean.BeanDefinition;
import org.myspring.factory.config.io.ResourceLoader;
import org.myspring.factory.config.reader.XmlBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

	private String configLocation;

	public ClassPathXmlApplicationContext(String configLocation) throws Exception {
		this(configLocation, new DefaultListableBeanFactory());
	}

	/**
	 * ioc xml的入口，跟AnnotationConfigApplicationContext区别就是AnnotationConfigApplicationContext需要单独对一些config类进行register，否则不知道扫描哪些类
	 * @param configLocation
	 * @param beanFactory
	 * @throws Exception
	 */
	public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}

	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
		for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
		}
	}

}