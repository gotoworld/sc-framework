package com.wu1g.config;

import com.wu1g.framework.config.AppConfig;
import org.beetl.core.ResourceLoader;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.fox.beetl.resource.SpringResourceLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.net.URL;
import java.util.Enumeration;

@AutoConfigureAfter(AppConfig.class)
@Configuration
public class BeetlConfig implements EnvironmentAware {
    private RelaxedPropertyResolver mvcResolver;
    @Override
    public void setEnvironment(Environment env) {
        this.mvcResolver = new RelaxedPropertyResolver(env, "spring.mvc.");
    }

    @Bean(name = "beetlSpringResourceLoader")
    public SpringResourceLoader getSpringResourceLoader() {
        return new SpringResourceLoader();
    }

    @Bean(initMethod = "init", name = "beetlGroupUtilConfiguration")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration(@Qualifier("beetlSpringResourceLoader") SpringResourceLoader springResourceLoader) {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver( new PathMatchingResourcePatternResolver());
        try {
            beetlGroupUtilConfiguration.setResourceLoader(springResourceLoader);
            beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource(AppConfig.getProperty("config.beetl")));
            return beetlGroupUtilConfiguration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlGroupUtilConfiguration") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix(mvcResolver.getProperty("view.prefix"));
        beetlSpringViewResolver.setSuffix(mvcResolver.getProperty("view.suffix"));
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        return beetlSpringViewResolver;
    }
}
