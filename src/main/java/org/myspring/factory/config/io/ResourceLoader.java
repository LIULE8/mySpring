package org.myspring.factory.config.io;

import java.net.URL;

public class ResourceLoader {
    /**
     * 用类加载器读取项目src下的文件
     * @param location
     * @return
     */
    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
