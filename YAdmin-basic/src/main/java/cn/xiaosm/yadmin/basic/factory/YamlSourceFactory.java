package cn.xiaosm.yadmin.basic.factory;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;

/**
 * yaml文件注入到实体类的工厂
 */
public class YamlSourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        if (resource == null) {
            return super.createPropertySource(name, resource);
        }
        return new YamlPropertySourceLoader()
            .load(resource.getResource().getFilename(), resource.getResource()).get(0);
    }
}