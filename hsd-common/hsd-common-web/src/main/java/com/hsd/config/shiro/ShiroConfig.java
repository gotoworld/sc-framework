package com.hsd.config.shiro;

import com.hsd.framework.cache.config.RedisProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

	/**
	 * 加载属性文件数据
	 */
	@Bean
	public RedisProperties shiroProperties() {
		return new RedisProperties();
	}

//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//		filterRegistration.setEnabled(true);
//		filterRegistration.addUrlPatterns("/*");
//		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//		return filterRegistration;
//	}
	@Bean(name = "sessionIdCookie")
	public SimpleCookie getSessionIdCookie() {
		SimpleCookie cookie = new SimpleCookie("sid");
		cookie.setPath("/");
		cookie.setMaxAge(1800000);
		cookie.setHttpOnly(true);
		return cookie;
	}

	/**
	 * 权限管理器
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		// 数据库认证的实现
		manager.setRealm(userRealm());
		// session 管理器
		manager.setSessionManager(sessionManager());
		// 缓存管理器
		manager.setCacheManager(redisCacheManager());
		return manager;
	}

	@Bean(name = "sessionManager")
	public WebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);//设置全局会话超时时间，默认30分钟(1800000)
		sessionManager.setDeleteInvalidSessions(true);//是否在会话过期后会调用SessionDAO的delete方法删除会话 默认true
		sessionManager.setSessionValidationInterval(1800000/2);//会话验证器调度时间
		sessionManager.setSessionValidationSchedulerEnabled(true);//定时检查失效的session
		sessionManager.setSessionIdCookie(getSessionIdCookie());//sessionIdCookie的实现,用于重写覆盖容器默认的JSESSIONID
//		ServletContainerSessionManager sessionManager = new ServletContainerSessionManager();
		return sessionManager;
	}
	//  密码加密算法 必须与数据库一致
	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(1);//加密次数
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}
	/**
	 * @see ShiroRealm--->AuthorizingRealm
	 */
	@Bean
	@DependsOn(value = { "lifecycleBeanPostProcessor", "shrioRedisCacheManager" })
	public ShiroRealm userRealm() {
		ShiroRealm userRealm = new ShiroRealm();
		userRealm.setCacheManager(redisCacheManager());
		userRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthorizationCachingEnabled(true);
		return userRealm;
	}

//	@Bean
//	public URLPermissionsFilter urlPermissionsFilter() {
//		return new URLPermissionsFilter();
//	}

	@Bean(name = "shrioRedisCacheManager")
	@DependsOn(value = "shiroRedisTemplate")
	public ShrioRedisCacheManager redisCacheManager() {
		ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(shiroRedisTemplate());
		cacheManager.createCache("hsd-shiro-cache:");
		return cacheManager;
	}

	@Bean(name = "shiroRedisTemplate")
	public RedisTemplate<byte[], byte[]> shiroRedisTemplate() {
		RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		return template;
	}

	/**
	 * Redis连接客户端(Session及Shiro缓存管理)
	 * 
	 * @return
	 */
	@Primary
	@Bean(name = "connectionFactory")
	@DependsOn(value = "shiroProperties")
	public RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory conn = new JedisConnectionFactory();
		conn.setDatabase(shiroProperties().getDatabase());
		conn.setHostName(shiroProperties().getHost());
		conn.setPassword(shiroProperties().getPassword());
		conn.setPort(shiroProperties().getPort());
		conn.setTimeout(shiroProperties().getTimeout());
		log.info("1.初始化Redis缓存服务器(登录用户Session及Shiro缓存管理)... ...");
		return conn;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * @see ShiroFilterFactoryBean
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());

		bean.setLoginUrl(shiroProps().getLoginUrl());
		bean.setSuccessUrl(shiroProps().getSuccessUrl());
		bean.setUnauthorizedUrl(shiroProps().getUnauthorizedUrl());

		bean.getFilterChainDefinitionMap().put("/error/*","anon");
		bean.getFilterChainDefinitionMap().putAll(shiroProps().getFilterChainDefinitionMap());
		return bean;
	}
	@Bean
	public ShiroProps shiroProps() {
		return new ShiroProps();
	}

	@Data
	@Configuration
	@NoArgsConstructor
	@ConfigurationProperties(prefix = "shiro")
	public static class ShiroProps{
		private Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
		private String loginUrl;
		private String successUrl;
		private String unauthorizedUrl;
	}
}