package com.hsd.framework.rabbit;

import com.hsd.framework.mq.MQQueue;
import com.hsd.framework.rabbit.config.ApmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * queue队列配置
 */
@Slf4j
public class RabbitQueue extends MQQueue {

    private RabbitAdmin rabbitAdmin;
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    private ApmqExchange exchange = ApmqExchange.CONTRACT_DIRECT;

    private Queue queue = null;
    private RabbitTemplate.ConfirmCallback callback = null;

    public RabbitQueue(String queueName) {
        this( queueName, false);
    }

    public RabbitQueue(String queueName, boolean autoDelete) {
        super(queueName, autoDelete);
    }

    /**
     * 初始化队列，绑定
     */
    protected void init() {
        rabbitAdmin = (RabbitAdmin) ApmqConfig.getBean("rabbitAdmin");
        rabbitMessagingTemplate = (RabbitMessagingTemplate) ApmqConfig.getBean("rabbitMessagingTemplate");

        Queue queue = new Queue(queueName, true, false, autoDelete);

        //将queue绑定到exchange
        String rountName = this.queueName; //AppConfig.getKey(this.queueName) ;
        Binding binding = null;
        if (this.exchange == ApmqExchange.CONTRACT_DIRECT) {
            DirectExchange directExchange = new DirectExchange(ApmqExchange.CONTRACT_DIRECT.name());
            rabbitAdmin.declareExchange(directExchange);
            binding = BindingBuilder.bind(queue).to(directExchange ).with(rountName );
        } else if (this.exchange == ApmqExchange.CONTRACT_TOPIC) {
            TopicExchange topicExchange = new TopicExchange(ApmqExchange.CONTRACT_DIRECT.name());
            rabbitAdmin.declareExchange(topicExchange);
            binding = BindingBuilder.bind(queue).to(topicExchange ).with(rountName );
        } else if (this.exchange == ApmqExchange.CONTRACT_FANOUT) {
            FanoutExchange fanoutExchange = new FanoutExchange(ApmqExchange.CONTRACT_DIRECT.name() );
            rabbitAdmin.declareExchange(fanoutExchange);
            binding = BindingBuilder.bind(queue).to(fanoutExchange );
        } else
            throw new IllegalArgumentException("rabbit mq 无效队列exchange");

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 发送一个消息，至本Queue
     * @param dto
     */
    public void doSend(final Object dto) {
        new Thread(() -> {
            this.rabbitMessagingTemplate.convertAndSend(this.exchange.name(), queueName, dto);
        }).start();

//        log.info("mq send queue={},dto={},by host={}", queueName, dto, this.rabbitMessagingTemplate.getRabbitTemplate().getConnectionFactory().getHost());
    }

}
