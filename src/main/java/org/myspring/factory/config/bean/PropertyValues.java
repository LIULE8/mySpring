package org.myspring.factory.config.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean的属性
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValues = new ArrayList<>(256);

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    /**
     * 这里的操作较为复杂，会做很多校验，然后merge，简化就直接设置
     *
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < propertyValues.size(); i++) {
            PropertyValue currentPv = propertyValues.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                // 省略merge操作，直接替换
                propertyValues.set(i, pv);
            }
        }
    }
}
