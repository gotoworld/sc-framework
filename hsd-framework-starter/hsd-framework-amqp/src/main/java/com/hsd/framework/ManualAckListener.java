package com.hsd.framework;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.listener.adapter.AbstractAdaptableMessageListener;

import java.io.IOException;

public abstract class ManualAckListener extends AbstractAdaptableMessageListener {

    public ManualAckListener() {
    }

    @Override
    public void onMessage(org.springframework.amqp.core.Message message, Channel channel) {

    }

    public void doAck(org.springframework.amqp.core.Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}