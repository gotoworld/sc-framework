package com.hsd.framework.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.session.web.http.SessionRepositoryFilter;

@Configuration
@EnableRedisHttpSession//(maxInactiveIntervalInSeconds = 36000)
public class RedisSessionConfig {
    @Autowired
    private  RedisProperties redisProperties;
    @Autowired
    private RedisConnectionFactory secondaryRedisConnectionFactory;
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;

    }
    /** Session设置 */
    @Bean
    public RedisHttpSessionConfiguration redisHttpSessionConfiguration(){
        RedisHttpSessionConfiguration redisHttpSessionConfiguration=new RedisHttpSessionConfiguration();
        redisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(redisProperties.getSessionExpire());
        return redisHttpSessionConfiguration;
    }
    @Bean
    public RedisOperationsSessionRepository sessionRepository() {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(secondaryRedisConnectionFactory);
        sessionRepository.setDefaultMaxInactiveInterval(redisProperties.getSessionExpire());// 设置session的有效时长
        return sessionRepository;
    }
    @Bean
    public SessionRepositoryFilter sessionRepositoryFilter(){
        SessionRepositoryFilter sessionRepositoryFilter=new SessionRepositoryFilter(sessionRepository());
        sessionRepositoryFilter.setHttpSessionStrategy(httpSessionStrategy());
        return sessionRepositoryFilter;
    }
    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        HeaderHttpSessionStrategy headerHttpSessionStrategy = new HeaderHttpSessionStrategy();
        headerHttpSessionStrategy.setHeaderName("X-Cache");
        return headerHttpSessionStrategy;
    }
}
