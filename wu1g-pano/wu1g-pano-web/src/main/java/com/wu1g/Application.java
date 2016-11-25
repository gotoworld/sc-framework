package com.wu1g;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.wu1g.framework.config.AppConfig;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

@AutoConfigureAfter(AppConfig.class)
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EnvironmentAware {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources( Application.class );
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	private RelaxedPropertyResolver mvcResolver;

	@Override
	public void setEnvironment(Environment env) {
		this.mvcResolver = new RelaxedPropertyResolver(env, "spring.mvc.");
	}


	@Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {

		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			String root =  patternResolver.getResource("classpath:/view/").getFile().toString();
			WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader(root);
			beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);

			beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:/conf/beetl.properties"));
			return beetlGroupUtilConfiguration;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(@Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
	/**
	 * Druid Filter配置
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter( new WebStatFilter() );
		filterRegistrationBean.addUrlPatterns( "/*" );
		filterRegistrationBean.addInitParameter( "exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*" );
		filterRegistrationBean.addInitParameter( "sessionStatMaxCount", "2000" );
		filterRegistrationBean.addInitParameter( "sessionStatEnable", "true" );
		filterRegistrationBean.addInitParameter( "principalSessionName", "session_user_key" );
		filterRegistrationBean.addInitParameter( "profileEnable", "true" );
		return filterRegistrationBean;
	}

	/**
	 * Druid Servlet
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean( dispatcherServlet );
		dispatcherServlet.setThrowExceptionIfNoHandlerFound( true );

		// 添加druidServlet
		registration.setServlet( new StatViewServlet() );
		registration.addInitParameter( "resetEnable", "true" );
		registration.addInitParameter( "loginUsername", "druid" );
		registration.addInitParameter( "loginPassword", "druid" );
		registration.addUrlMappings( "/druid/*" );
		return registration;
	}
	@Bean
	public DruidStatInterceptor druidStatInterceptor() {
		return new DruidStatInterceptor();
	}

	/**
	 * 添加Spring监控 监控所有包含Dao和Service的Bean
	 * @return
	 */
	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		creator.setProxyTargetClass( true );
		creator.setBeanNames( new String[] { "*Dao*", "*Service*" } );
		creator.setInterceptorNames( "druidStatInterceptor" );

		return creator;
	}
}
