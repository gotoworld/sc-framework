package com.wu1g.config;

import com.wu1g.framework.interceptor.UserRememberInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Slf4j
@Order(99999)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new MyCustomizer();
    }
    private static class MyCustomizer implements EmbeddedServletContainerCustomizer {
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.EXPECTATION_FAILED, "/error/500"));
        }
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("==拦截器注册==");
        registry.addInterceptor(new UserRememberInterceptor()).addPathPatterns("/h/**");

        super.addInterceptors(registry);
    }
}
