package com.zwjlearn.rabbitmq.constant;

public interface RabbitQueues {
    String DIRECT_MODE_QUEUE_ONE = "direct.queue.one";

    /**
     * 延迟队列
     */
    String DELAY_QUEUE = "delay.queue";

    /**
     * 延迟队列交换器
     */
    String DELAY_QUEUE_EXCHANGE = "delay.queue.exchange";
}
