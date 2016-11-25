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

package com.wu1g.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.wu1g.framework.security.AES;
import com.wu1g.framework.security.DES;
import com.wu1g.framework.security.DES3;
import com.wu1g.framework.util.Converter;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * MyBatis基础配置
 *
 * @author liuzh
 * @since 2015-12-19 10:11
 */
@Configuration
@AutoConfigureAfter(AppConfig.class)
@EnableTransactionManagement
@Slf4j
@Order(-999)
public class JdbcConfig implements EnvironmentAware {
    private RelaxedPropertyResolver dataSourceResolver;
    private RelaxedPropertyResolver myBatisResolver;

    @Override
    public void setEnvironment(Environment env) {
        this.dataSourceResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        this.myBatisResolver = new RelaxedPropertyResolver(env, "mybatis.");
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    @Order(1)
    public DataSource writeDataSource() throws SQLException {
        log.info("注入 druid！！！");

        DruidDataSource dataSource = new DruidDataSource();
        String val = dataSourceResolver.getProperty("url");
        if (val != null)
            dataSource.setUrl(val);
        val = dataSourceResolver.getProperty("username");
        if (val != null)
            dataSource.setUsername(val);//用户名
        val = dataSourceResolver.getProperty("password");
        if (val != null) {
            val = AppConfig.checkPassword(val);
            dataSource.setPassword(val);//密码
        }
        val = dataSourceResolver.getProperty("driver-class-name");
        if (val != null)
            dataSource.setDriverClassName(val);
        val = dataSourceResolver.getProperty("initialSize");
        if (val != null)
            dataSource.setInitialSize(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("minIdle");
        if (val != null)
            dataSource.setMinIdle(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("maxActive");
        if (val != null)
            dataSource.setMaxActive(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("maxWait");
        if (val != null)
            dataSource.setMaxWait(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("timeBetweenEvictionRunsMillis");
        if (val != null)
            dataSource.setTimeBetweenEvictionRunsMillis(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("minEvictableIdleTimeMillis");
        if (val != null)
            dataSource.setMinEvictableIdleTimeMillis(Converter.stringToInteger(val));
        val = dataSourceResolver.getProperty("validationQuery");
        if (val != null)
            dataSource.setValidationQuery(val);
        val = dataSourceResolver.getProperty("testOnBorrow");
        if (val != null)
            dataSource.setTestOnBorrow(Boolean.getBoolean(val));
        val = dataSourceResolver.getProperty("testWhileIdle");
        if (val != null)
            dataSource.setTestWhileIdle(Boolean.getBoolean(val));
        val = dataSourceResolver.getProperty("testOnReturn");
        if (val != null)
            dataSource.setTestOnReturn(Boolean.getBoolean(val));
        val = dataSourceResolver.getProperty("poolPreparedStatements");
        if (val != null)
            dataSource.setPoolPreparedStatements(Boolean.getBoolean(val));
        val = dataSourceResolver.getProperty("maxPoolPreparedStatementPerConnectionSize");
        if (val != null)
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(Converter.stringToInteger(val));
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        val = dataSourceResolver.getProperty("filters");
        if (val != null)
            dataSource.setFilters(val);

        val = dataSourceResolver.getProperty("connectionProperties");
        if (val != null) {
            String[] ps = val.split(";");
            Properties props = new Properties();
            for (String line : ps) {
                String[] ones = line.split(":");
                props.put(ones[0].trim(), ones[1].trim());
            }
            dataSource.setConnectProperties(props);
        }

        val = dataSourceResolver.getProperty("useGlobalDataSourceStat");
        if (val != null) {
            dataSource.setUseGlobalDataSourceStat(Boolean.getBoolean(val));
        }
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    @Order(2)
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //分页插件
        log.info("注入 PageHelper ！！！");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        String val = myBatisResolver.getProperty("mapperLocations");
        log.info("注入 SqlSessionFactory ！ mapperLocations: " + val);
        try {
            if (val != null)
                bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(val));
        } catch (Exception e) {
            log.error("mapperLocations: " + e.getMessage());
        }
        /**myBatis shiro*/
        val = myBatisResolver.getProperty("configLocation");
        try {
            if (val != null)
                bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(val));
        } catch (Exception e) {
            log.error("configLocation: " + e.getMessage());
        }
        //typeAliasesPackage
        bean.setTypeAliasesPackage("com.wu1g");


        try {
            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    @Order(3)
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Order(4)
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return sqlSessionFactory.openSession();
    }


    /**
     * 阿里DruidDataSrouce监控、统计配置: 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    @Order(5)
    public ServletRegistrationBean registStatView() {
        log.info("注入 Druid StatViewServlet ！！！");
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");


        //添加初始化参数：initParams

        //白名单：
//        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin2");
        servletRegistrationBean.addInitParameter("loginPassword", "admin22016");
//        //是否能够重置数据.
//        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 阿里DruidDataSrouce监控: 统计配置 注册一个：filterRegistrationBean
     *
     * @return:
     */
    @Bean
    @Order(6)
    public FilterRegistrationBean registStatFilter() {
        log.info("注入 Druid WebStatFilter ！！！");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("/error", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
