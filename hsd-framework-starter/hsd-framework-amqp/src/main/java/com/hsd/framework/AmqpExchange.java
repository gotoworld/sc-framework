package com.hsd.framework;

/**
 * exchange交换机配置
 */
public enum AmqpExchange {

    /**
     * 合同exchange
     */
    CONTRACT_FANOUT,
    CONTRACT_TOPIC,
    CONTRACT_DIRECT,

}
