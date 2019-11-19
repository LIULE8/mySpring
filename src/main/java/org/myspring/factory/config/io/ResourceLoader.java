package org.myspring.factory.config.io;

import java.net.URL;

public class ResourceLoader {
    /**
     * 用类加载器读取项目下的文件
     * @param location
     * @return
     */
    public Resource getResource(String location){
        Class<? extends ResourceLoader> aClass = this.getClass();
        System.out.println("Class: " + aClass);
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println("ClassLoader: " + classLoader);
        URL resource = classLoader.getResource(location);
        System.out.println("URL resource: " + resource);
        return new UrlResource(resource);
    }
}
