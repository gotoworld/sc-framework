package com.wu1g.framework.web.config;


import com.cddang.framework.cache.config.CacheConfig;
import com.cddang.framework.config.AppConfig;
import com.cddang.framework.util.IpUtil;
import com.cddang.framework.web.interceptor.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.RemoteAddrValve;
import org.apache.catalina.valves.RemoteIpValve;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by leo on 16/6/13.
 * 初始化Web 拦截器bean
 */

@Configuration
@Slf4j
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    AppConfig appConfig;
    @Autowired
    AuthInterceptor authInterceptor;

    @Bean(name = "ipUtil")
    public IpUtil ipUtil() {

        String ips = AppConfig.getProperty("common.notAuthAddrs");
        if (ips == null)
            ips = "10.*,192.168.*,0.0.*,127.0.*,101.204.230.251";

        IpUtil ipUtil = new IpUtil();
        ipUtil.addAllow(ips);

        log.info("注入 ipUtil[{}]", ips);
        return ipUtil;
    }

    @Bean(name = "authInterceptor")
    public AuthInterceptor authInterceptor() {
        AuthInterceptor authInterceptor = new AuthInterceptor();
        log.info("注入 authInterceptor");
        return authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor );
    }

}
