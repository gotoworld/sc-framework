/*
 *  类描述待补充.
 *
 * VERSION  DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------
 * 1.00     2016-3-26  wu1g119      程序.发布
 * -------- ----------- --------------- ------------------------------------
 * Copyright 2016 baseos  System. - All Rights Reserved.
 *
 */
package com.wu1g.config;

import com.wu1g.framework.interceptor.UserRememberInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * <p>
 * 类功能说明待补充
 * </p>
 * <dl>
 * [功能概要]
 * <dt>功能1</dt>
 * </dl>
 */
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {
	private static final Log	log	= LogFactory.getLog( MyWebMvcConfigurer.class );
	@SuppressWarnings("deprecation")
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		log.info( "==添加错误状态处理页面==" );
		container.addErrorPages( new ErrorPage( HttpStatus.NOT_FOUND, "/error/404" ) );
		container.addErrorPages( new ErrorPage( HttpStatus.EXPECTATION_FAILED, "/error/500" ) );
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		log.info( "==拦截器注册==" );
        registry.addInterceptor(new UserRememberInterceptor()).addPathPatterns("/h/**");

        super.addInterceptors(registry);

    }

}
