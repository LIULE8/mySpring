package org.myspring.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean的属性
 *
 * @author Leo Liu
 * @create 19/11/2019
 */
public class PropertyValues {

  private final List<PropertyValue> propertyValueList = new ArrayList<>(256);

  public void addPropertyValues(String propertyName, Object propertyValue) {
    PropertyValue pv = new PropertyValue(propertyName, propertyValue);
    for (int i = 0; i < propertyValueList.size(); i++) {
      PropertyValue currentPv = propertyValueList.get(i);
      if (currentPv.getName().equals(pv.getName())) {
        // 省略merge操作，直接替换
        propertyValueList.set(i, pv);
      }
    }
  }

  public List<PropertyValue> getPropertyValueList() {
    return propertyValueList;
  }
}
