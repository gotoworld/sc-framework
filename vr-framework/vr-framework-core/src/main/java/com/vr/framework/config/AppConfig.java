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

package com.vr.framework.config;

import com.vr.framework.security.AES;
import com.vr.framework.security.DES;
import com.vr.framework.security.DES3;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

import java.util.Map;

/**
 * Application 自定义配置
 */
@Configuration
@Order(-1000)
@Slf4j
public class AppConfig implements ApplicationContextAware, EnvironmentAware {
    public static final String keyPrefix = "vr.";
    private static final String prikey="A1B2C3D4E5F60708";
    private static ApplicationContext applicationContext;
    private static RelaxedPropertyResolver appProperty;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;
    }

    @Override
    public void setEnvironment(Environment env) {
        this.appProperty = new RelaxedPropertyResolver(env, keyPrefix);
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getKey(String key) {
        if (key == null) {
            return null;
        }
        String prefix = null;
        try {
            prefix = getProperty("common.appPrefix");
        } catch (Exception e) {
        }
        return (StringUtils.isEmpty(prefix) ? key : prefix + ":" + key);
    }

    /**
     * 检查配置数据password，是否采用默认加密。如加密，则解密
     *
     * @param password
     * @return
     */
    public static String checkPassword(String password) {
        int idx = password.indexOf(":");
        if (idx > 0) {
            if (password.startsWith("AES:")) {
                password = AES.decrypt(password.substring(idx + 1).trim(), prikey);
            } else if (password.startsWith("DES:")) {
                password = DES.decrypt(password.substring(idx + 1).trim(),prikey);
            } else if (password.startsWith("DES3:")) {
                password = DES3.decrypt(password.substring(idx + 1).trim(),prikey);
            }
        }
        return password;
    }

    @Bean
    @Order(2)
    @ConditionalOnMissingBean
    public AppConfig createAppConfig() {
        AppConfig appConfig = new AppConfig();
        log.info("注入 appConfig ");
        return appConfig;
    }

    public static String getProperty(String key) {
        return appProperty.getProperty(key);
    }

    public static String getProperty(String name, String defaulValue) {
        return appProperty.getProperty(name, defaulValue);
    }

    public static <T> T getProperty(String name, Class<T> targetType) {
        return appProperty.getProperty(name, targetType);
    }

    public static <T> T getProperty(String name, Class<T> targetType, T defaulValue) {
        return appProperty.getProperty(name, targetType, defaulValue);
    }

    public static boolean containsProperty(String key) {
        return appProperty.containsProperty(key);
    }

    public static Map<String, Object> getSubProperties(String keyPrefix) {
        return appProperty.getSubProperties(keyPrefix);
    }
}
