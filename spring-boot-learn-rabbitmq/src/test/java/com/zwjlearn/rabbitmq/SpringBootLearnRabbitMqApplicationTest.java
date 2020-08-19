package com.zwjlearn.rabbitmq;

import com.zwjlearn.rabbitmq.constant.RabbitQueues;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootLearnRabbitMqApplicationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void sendDirect() {
        rabbitTemplate.convertAndSend(RabbitQueues.DIRECT_MODE_QUEUE_ONE,"Hello direct queue");
    }

    @Test
    public void sendDelayQueue() {
        log.info("send message");
        rabbitTemplate.convertAndSend(RabbitQueues.DELAY_QUEUE,"Hello delay queue",message -> {
            message.getMessageProperties().setHeader("x-delay",5000);
            return message;
        });
    }
}
