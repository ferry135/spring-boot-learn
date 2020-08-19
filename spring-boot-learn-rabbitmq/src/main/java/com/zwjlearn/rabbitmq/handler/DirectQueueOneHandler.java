package com.zwjlearn.rabbitmq.handler;

import com.rabbitmq.client.Channel;
import com.zwjlearn.rabbitmq.constant.RabbitQueues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RabbitListener(queues = {RabbitQueues.DIRECT_MODE_QUEUE_ONE,RabbitQueues.DELAY_QUEUE})
@Component
public class DirectQueueOneHandler {

    @RabbitHandler
    public void manualAck(String msg, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("手动ack，接收消息：{}",msg);
        try {
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            try {
                channel.basicRecover();
            } catch (IOException ex) {
                log.error(ex.getMessage(),ex);
            }
        }
    }
}
