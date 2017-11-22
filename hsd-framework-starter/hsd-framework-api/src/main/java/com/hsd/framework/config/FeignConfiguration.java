package com.hsd.framework.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class FeignConfiguration {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
                if(requestAttributes==null) return;
                HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        if(!"#X-Real-IP#Proxy-Client-IP#WL-Proxy-Client-IP#X-Forwarded-For#Authorization#X-Cache#".toLowerCase().contains("#"+(""+name).toLowerCase()+"#")) continue;
                        Enumeration<String> values = request.getHeaders(name);
                        while (values.hasMoreElements()) {
                            String value = values.nextElement();
                            requestTemplate.header(name, value);
                        }
                    }
                }
            }
        };
    }
}