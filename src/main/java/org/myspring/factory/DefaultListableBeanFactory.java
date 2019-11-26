package org.myspring.factory;

import org.myspring.factory.config.bean.BeanDefinition;
import org.myspring.factory.config.bean.BeanReference;
import org.myspring.factory.config.bean.PropertyValue;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
@SuppressWarnings("serial")
public class DefaultListableBeanFactory extends AbstractBeanFactory implements Serializable {

    private static final long serialVersionUID = -5463338182566609633L;

    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }

}
