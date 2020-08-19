package com.zwjlearn.rabbitmq.cofig;

import com.google.common.collect.Maps;
import com.zwjlearn.rabbitmq.constant.RabbitQueues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class RabbitMqConfig {

    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        // 消息发送的返回
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }
    @Bean
    public Queue directOneQueue(){
        return new Queue(RabbitQueues.DIRECT_MODE_QUEUE_ONE);
    }
    @Bean
    public Queue delayQueue(){
        return new Queue(RabbitQueues.DELAY_QUEUE,true);
    }
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitQueues.DELAY_QUEUE_EXCHANGE,"x-delayed-message",true,false);
    }

    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange delayExchange) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(RabbitQueues.DELAY_QUEUE).noargs();
    }
}
