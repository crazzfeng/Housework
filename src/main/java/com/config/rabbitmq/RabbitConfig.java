package com.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yufeng li
 * @title: RabbitConfig
 * @description:
 * @date 2020/9/16 14:28
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    /**
     * 实时消息交换机
     */
    static final String directExchange = "amq.direct";

    /**
     * 实时消息队列
     */
    static final String messageQueue = "message.queue";

    /**
     * 实时消息routingkey
     */
    static final String messageRoutingKey = "message.direct.queue";

    /**
     * 延迟消息交换机
     */
    static final String directDelayExchange = "amq.delay.direct";

    /**
     * 延迟消息队列
     */
    static final String messageDelayQueue = "message.delay.queue";

    /**
     * 延迟消息routingkey
     */
    static final String messageDelayRoutingKey = "message.direct.delay.queue";

    @Bean
    Queue messqgeQueue() {
        return QueueBuilder.durable(messageQueue).build();
    }

    @Bean
    Exchange directExchange() {
        return ExchangeBuilder.directExchange(directExchange).durable(true).build();
    }

    @Bean
    Binding messageBind(Queue messqgeQueue, Exchange directExchange) {
        return BindingBuilder.bind(messqgeQueue).to(directExchange).with(messageRoutingKey).noargs();
    }

    @Bean
    Queue messageDelayQueue() {
        return QueueBuilder.durable(messageDelayQueue)
                .deadLetterExchange(directExchange)
                .deadLetterRoutingKey(messageRoutingKey)
                .ttl(1000)
                .build();
    }

    @Bean
    Exchange directDelayExchange() {
        Map<String, Object> argss = new HashMap<String, Object>();
        argss.put("x-delayed-type", "direct");
        AbstractExchange exchange = new AbstractExchange(directExchange, true, false, argss) {
            @Override
            public String getType() {
                return "x-delayed-message";
            }
        };
        return exchange;
    }

    @Bean
    Binding messageDelayBind(Queue messageDelayQueue, Exchange directDelayExchange) {
        return BindingBuilder.bind(messageDelayQueue).to(directDelayExchange).with(messageDelayRoutingKey).noargs();
    }


}
