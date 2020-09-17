package com.config.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yufeng li
 * @title: MessageProvider
 * @description:
 * @date 2020/9/16 15:30
 */
@Component
public class MessageProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message){
        Object receive = amqpTemplate.convertSendAndReceive(RabbitConfig.directExchange, RabbitConfig.messageRoutingKey, message);
        System.out.println("发送消息后的返回值："+receive.toString());
    }


    public void sendDelayMessage(String message,Integer delay){
        Object receive = amqpTemplate.convertSendAndReceive(RabbitConfig.directDelayExchange, RabbitConfig.messageDelayRoutingKey, msg -> {
            //消息延迟发送时间
            msg.getMessageProperties().setDelay(delay);
            //消息过期时间 防止消息阻塞
            msg.getMessageProperties().setExpiration("10000");
            return msg;
        });
        System.out.println("发送消息后的返回值："+receive.toString());
    }
}
