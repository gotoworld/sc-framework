package com.hsd.framework;

import lombok.extern.slf4j.Slf4j;

/**
 * queue队列配置
 */
@Slf4j
public abstract class MQQueue {

    private String exchange;

    protected String queueName;

    protected boolean autoDelete = false;


    public MQQueue(String queueName) {
        this(queueName, false);
    }

    public MQQueue(String queueName, boolean autoDelete) {
        this.queueName = queueName;
        this.autoDelete = autoDelete;
        init();
    }


    /**
     * 初始化队列，绑定
     */
    protected abstract void init();

    public String getName() {
        return this.queueName;
    }

    public String getExchange() {
        return this.exchange;
    }

    /**
     * 发送一个消息，至本Queue
     */
    public void send(final AbstractEntity dto) {
        doSend(dto);
    }

    /**
     * 发送一个消息，至本Queue
     */
    public void send(final String value) {
        doSend(value);
    }

    protected abstract void doSend(Object obj);
}
