/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wu1g.config;

import com.google.common.collect.Maps;
import com.wu1g.framework.config.AppConfig;
import com.wu1g.web.controller.ShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.Map;

@Configuration
@AutoConfigureAfter(AppConfig.class)
@Slf4j
@Order(-998)
public class ShiroConfig {
//    /**
//     * FilterRegistrationBean
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
//        return filterRegistration;
//    }

    /**
     * @return
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/admin/init");
        bean.setSuccessUrl("/h/index");
        bean.setUnauthorizedUrl("/error/noauth");

//        Map<String, Filter> filters = Maps.newHashMap();
//        filters.put("perms", urlPermissionsFilter());
//        filters.put("anon", new AnonymousFilter());
//        bean.setFilters(filters);

        Map<String, String> chains = Maps.newHashMap();
        chains.put("/error/noauth", "anon");
        chains.put("/admin/init", "anon");
        chains.put("/admin/logout", "logout");
        chains.put("/admin/login", "anon");
        chains.put("/css/**", "anon");
        chains.put("/h/**", "authc");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    /**
     * @return
     * @see org.apache.shiro.mgt.SecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        manager.setCacheManager(cacheManager());
        manager.setSessionManager(defaultWebSessionManager());
        return manager;
    }

    /**
     * @return
     * @see DefaultWebSessionManager
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * @return
     * @see ShiroRealm --->AuthorizingRealm
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor")
    public ShiroRealm userRealm() {
        ShiroRealm userRealm = new ShiroRealm();
        userRealm.setCacheManager(cacheManager());
        return userRealm;
    }

    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:conf/ehcache.xml");
        return cacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
