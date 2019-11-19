package org.myspring;

import org.myspring.context.ApplicationContext;
import org.myspring.context.xml.ClassPathXmlApplicationContext;

/**
 * @author Leo Liu
 * @create 19/11/2019
 */
public class Test {

  public static void main(String[] args) throws Exception {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("myioc.xml");
    City city = (City) applicationContext.getBean("city");
    System.out.println(city);
  }
}
