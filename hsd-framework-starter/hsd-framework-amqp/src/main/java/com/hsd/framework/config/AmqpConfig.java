package com.hsd.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.messaging.converter.SimpleMessageConverter;

@Configuration
@Slf4j
public class AmqpConfig implements EnvironmentAware, ApplicationContextAware {
    private RelaxedPropertyResolver mqResolver;
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        applicationContext = appContext;

    }

    @Override
    public void setEnvironment(Environment env) {
        this.mqResolver = new RelaxedPropertyResolver(env, "spring.rabbitmq.");
    }

    @Bean
    @Order(1)
    public ConnectionFactory connectionFactory() {
        log.info("注入 RabbitMQ connectionFactory");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        String val = mqResolver.getProperty("addresses");
        if (val != null)
            connectionFactory.setAddresses(val);
        else {
            val = mqResolver.getProperty("host");
            if (val != null)
                connectionFactory.setHost(val);
            val = mqResolver.getProperty("port");
            if (val != null)
                connectionFactory.setPort(Integer.parseInt(val ));
        }
        val = mqResolver.getProperty("virtual-host");
        if (val != null)
            connectionFactory.setVirtualHost(val);
        val = mqResolver.getProperty("password");
        if (val != null) {
            val = AppConfig.checkPassword(val );
            connectionFactory.setPassword(val );
        }
        val = mqResolver.getProperty("username");
        if (val != null)
            connectionFactory.setUsername(val );
        return connectionFactory;
    }

    public static Object getBean(String key) {
        return applicationContext.getBean(key);
    }

    @Bean
    @Order(2)
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        log.info("注入 rabbitAdmin");
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        return rabbitAdmin;
    }

    /**
     * 生产者用
     */
    @Bean
    @Order(3)
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        log.info("注入 RabbitMessagingTemplate");

        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

    public static SimpleMessageConverter jackson2Converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        return converter;
    }
}
