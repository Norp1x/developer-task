package com.google.service.service.impl;

import com.google.service.service.KafkaConsumerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by Norpix on 24.11.2024.
 * Description:
 */
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    public void consumeMessage() {
    }

    @KafkaListener(topics = "search-topic", groupId = "search-service")
    @Override
    public void consumeMessage(String message) {
        System.out.println("Consumed message from topic: " + message);
    }
}
