package com.study.rabbitmq.controllers;

import com.study.rabbitmq.dtos.TestDTO;
import com.tradeshift.amqp.rabbit.handlers.RabbitTemplateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${spring.rabbitmq.custom.test-event.exchange}")
    private String exchangeSomeEvent;

    @Value("${spring.rabbitmq.custom.test-event.queueRoutingKey}")
    private String routingKeySomeEvent;

    private final RabbitTemplateHandler rabbitTemplateHandler;

    public TestController(RabbitTemplateHandler rabbitTemplateHandler) {
        this.rabbitTemplateHandler = rabbitTemplateHandler;
    }

    @PostMapping
    public void produce(@RequestBody TestDTO dto) {
        rabbitTemplateHandler.getRabbitTemplate("test-event").convertAndSend(exchangeSomeEvent, routingKeySomeEvent, dto.getMessage());
    }
}
