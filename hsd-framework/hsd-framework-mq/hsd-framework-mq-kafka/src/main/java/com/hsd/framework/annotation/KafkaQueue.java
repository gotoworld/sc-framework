package com.hsd.framework.annotation;

import com.hsd.framework.mq.MQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Created by Administrator on 17-6-15.
 */
public class KafkaQueue extends MQQueue {
    @Autowired
    KafkaTemplate kafkaTemplate;

    public KafkaQueue(String queueName) {
        this( queueName, false);
    }

    public KafkaQueue(String queueName, boolean autoDelete) {
        super(queueName, autoDelete);
    }

    @Override
    protected void doSend(Object obj) {
        new Thread(() -> {
            kafkaTemplate.send(this.queueName, obj);
        }).start();

    }

    @Override
    protected void init() {

    }
}
