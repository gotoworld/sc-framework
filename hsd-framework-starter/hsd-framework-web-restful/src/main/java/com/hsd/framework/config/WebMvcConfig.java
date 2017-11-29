package com.hsd.framework.config;

import com.hsd.framework.interceptor.JwtInterceptor;
import lombok.extern.slf4j.Slf4j;
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
        return (container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages( new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        });
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (log.isDebugEnabled()) log.debug("==拦截器注册==");
        registry.addInterceptor(new JwtInterceptor()).addPathPatterns("/api/**","/boss/**");//权限验证拦截器

        super.addInterceptors(registry);
    }
}
