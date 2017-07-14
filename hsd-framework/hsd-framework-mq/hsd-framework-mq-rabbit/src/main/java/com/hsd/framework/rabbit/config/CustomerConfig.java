package com.hsd.framework.rabbit.config;

import com.hsd.framework.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 */
@Configuration
@EnableRabbit
@Order(41)
@Slf4j
public class CustomerConfig  {
    @Autowired
    AppConfig appConfig;

//    @Bean(name = "manualAckContainer")
//    @Autowired
//    public SimpleMessageListenerContainer manualAckContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory );
////        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                byte[] body = message.getBody();
//                System.out.println("receive msg : " + new String(body));
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
//            }
//        });
//        return container;
//    }

//    @Bean(name = "autoAckContainer")
//    @Autowired
//    public SimpleMessageListenerContainer autoAckContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory );
////        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(1);
//        container.setConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO); //设置确认模式手工确认
//
//        return container;
//    }

//    //    @Bean
//    public DefaultMessageHandlerMethodFactory defaultMessageHandlerMethodFactory() {
//        log.info("注入： Rabbitmq - DefaultMessageHandlerMethodFactory");
//        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
//        factory.setMessageConverter(new SimpleMessageConverter() );
//
//        return factory;
//    }

    @Bean(name = "autoAckContainerFactory")
    @Autowired
    public SimpleRabbitListenerContainerFactory autoAckContainerFactory(ConnectionFactory connectionFactory) {
        log.info("注入：Rabbitmq - autoAckContainerFactory");
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter() );

        // factory.setPrefetchCount(5);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        return factory;
    }

//    @Bean(name = "manualAckContainerFactory")
//    @Autowired
//    public SimpleRabbitListenerContainerFactory manualAckContainerFactory(ConnectionFactory connectionFactory) {
//        log.info("注入：Rabbitmq - manualAckContainerFactory");
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter() );
//
//        // factory.setPrefetchCount(5);
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        return factory;
//    }

//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
//        log.info("注册：Rabbitmq - DefaultMessageHandlerMethodFactory");
//        registrar.setContainerFactoryBeanName("simpleRabbitListenerContainerFactory");
//
//        registrar.setMessageHandlerMethodFactory((DefaultMessageHandlerMethodFactory) AppConfig.getApplicationContext().getBean("defaultMessageHandlerMethodFactory"));
//    }

}
