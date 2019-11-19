package org.myspring.factory.config.reader;

import org.myspring.factory.config.resource.Resource;
import org.myspring.factory.config.resource.UrlResource;

import java.net.URL;

public class DefaultResourceLoader {
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
