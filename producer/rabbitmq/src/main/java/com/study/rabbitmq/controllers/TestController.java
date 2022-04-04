package com.study.rabbitmq.controllers;

import com.study.rabbitmq.dtos.TestDTO;
import com.study.rabbitmq.producer.QueueSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final QueueSender producer;

    public TestController(QueueSender producer) {
        this.producer = producer;
    }

    @PostMapping
    public void produce(@RequestBody TestDTO dto) {
        producer.send(dto.getMessage());
    }
}
