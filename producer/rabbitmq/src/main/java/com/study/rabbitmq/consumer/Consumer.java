package com.study.rabbitmq.consumer;

import com.tradeshift.amqp.annotation.EnableRabbitRetryAndDlq;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = "${spring.rabbitmq.custom.test-event-consumer.queue}")
    @EnableRabbitRetryAndDlq(event = "test-event-consumer")
    public void onMessage(String message) {
        System.out.println(message);
    }
}
