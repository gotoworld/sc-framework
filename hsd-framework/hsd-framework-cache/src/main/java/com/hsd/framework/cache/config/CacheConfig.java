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

package com.hsd.framework.cache.config;

import com.hsd.framework.cache.CachePoolManager;
import com.hsd.framework.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 */
@Configuration
@Slf4j
@Order(-998)
public class CacheConfig implements EnvironmentAware, ApplicationContextAware {
    @Autowired
    AppConfig appConfig;

    private RelaxedPropertyResolver redisResolver;
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.redisResolver = new RelaxedPropertyResolver(env, "redis.");
    }

    public static Object getBean(String key) {
        return applicationContext == null ? null : applicationContext.getBean(key);
    }

    public JedisPoolConfig createPoolConfig(String prefix) throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();

        String val = redisResolver.getProperty(prefix + ".config.maxActive");
        int v = 8;
        try{
            v = Integer.parseInt(val);
        }catch(Exception e) {
        }
        config.setMaxTotal(v);
        val = redisResolver.getProperty(prefix + ".config.maxIdle");
        v = 8;
        try{
            v = Integer.parseInt(val);
        }catch(Exception e) {
        }
        config.setMaxIdle(v);
        val = redisResolver.getProperty(prefix + ".config.minIdle");
        v = 0;
        try{
            v = Integer.parseInt(val);
        }catch(Exception e) {
        }
        config.setMinIdle(v);
        val = redisResolver.getProperty(prefix + ".config.maxWait");
        v = 2000;
        try{
            v = Integer.parseInt(val);
        }catch(Exception e) {
        }
        config.setMaxWaitMillis(v);
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheConfig cacheConfig() {
        return new CacheConfig();
    }

    /**
     * 配置多Redis连接池（包括：JedisPool、JedisSentinelPool，不支持SharedJedisPool），使用JedisPoolManager进行管理
     * @return
     * @throws Exception
     */
    @Bean(name = {"cachePoolManager"})
    @Order(1)
    public CachePoolManager createJedisPool() throws Exception {
        log.info("注入 CachePoolManager 配置多Redis（JedisPool）连接池！！！");
        CachePoolManager poolManager = new CachePoolManager();

        int index = 1;
        String prefix = "jedisPool_" + index;
        String name = redisResolver.getProperty(prefix + ".name");

        while(name != null) {
            JedisPoolConfig jedisPoolConfig = createPoolConfig(prefix);

            String host = redisResolver.getProperty(prefix + ".host");
            String s_port = redisResolver.getProperty(prefix + ".port");
            int port = 6379;
            try {
                port = Integer.parseInt(s_port);
            }catch(Exception e){}
            String s_timeout = redisResolver.getProperty(prefix + ".timeout");
            int timeout = 2000;
            try {
                timeout = Integer.parseInt(s_timeout);
            }catch(Exception e){}
            String password = redisResolver.getProperty(prefix + ".password");
            if (password != null) {
                password = AppConfig.checkPassword(password);
            }
            JedisPool pool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
            poolManager.put(name, pool);

            index++;
            prefix = "jedisPool_" + index;
            name = redisResolver.getProperty(prefix + ".name");
        }

        /**sentinel 连接池（支持主从）*/
//        try {
            index = 1;
            prefix = "sentinelPool_" + index;
            name = redisResolver.getProperty(prefix + ".name");

            while (name != null) {
                JedisPoolConfig jedisPoolConfig = createPoolConfig(prefix);
                String sentinelName = redisResolver.getProperty(prefix + ".sentinelName");

                String s_timeout = redisResolver.getProperty(prefix + ".timeout");
                int timeout = 2000;
                try {
                    timeout = Integer.parseInt(s_timeout);
                } catch (Exception e) {
                }
                String password = redisResolver.getProperty(prefix + ".password");
                if (password != null) {
                    password = AppConfig.checkPassword(password);
                }
                String val = redisResolver.getProperty(prefix + ".nodes");
                String[] serverArray = val.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
                Set<String> nodes = new HashSet<>();
                for (String ipPort : serverArray) {
                    String[] ipPortPair = ipPort.split(":");
                    nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())).toString());
                }

                JedisSentinelPool pool = new JedisSentinelPool(sentinelName, nodes, jedisPoolConfig, timeout, password);
                poolManager.put(name, pool);
                index++;
                prefix = "sentinelPool_" + index;
                name = redisResolver.getProperty(prefix + ".name");
            }
//        }catch(Exception e){
//            log.error(e.getMessage(), e);
//        }

        return poolManager;
    }



    /**------以下是jedis cluster-----*/
//    @Bean
//    @Order(2)
//    public JedisCluster createJedisCluster(JedisPoolConfig jedisPoolConfig) throws Exception {
//        log.info("注入Spring JedisCluster！！！");
//
//        String val = redisResolver.getProperty("cluster.nodes");
//
//        String[] serverArray = val.split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
//        Set<HostAndPort> nodes = new HashSet<>();
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        val = redisResolver.getProperty("password");
//        String password = val == null ? "" : val;
//
//        val = redisResolver.getProperty("timeout");
//        Integer timeout = val == null ? 2000 : Integer.parseInt(val);
//
//        JedisCluster cluster = new JedisCluster(nodes, timeout, timeout, 5, password, jedisPoolConfig);
//
//        return cluster;
//    }
//
//    @Bean
//    @Order(3)
//    private JedisClusterConnection getJedisConnectionFactory(JedisCluster jedisCluster){
//        log.info("创建 JedisClusterConnection！！！");
//        JedisClusterConnection clusterConnection = new JedisClusterConnection(jedisCluster);
//        return clusterConnection;
//    }
}
