package com.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yufeng li
 * @title: MessageConsumer
 * @description:
 * @date 2020/9/16 15:30
 */
@Component
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfig.messageQueue)
    public String receiveMessage(String message){
        System.out.printf(message);
        return message;
    }

    @RabbitListener(queues = RabbitConfig.messageDelayQueue)
    public String receiveDelayMessage(String message){
        System.out.printf(message);
        return message;
    }
}
