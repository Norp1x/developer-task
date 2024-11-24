package com.search.service.service.impl;

import com.search.service.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Norpix on 24.11.2024.
 * Description: Implementation of the {@link KafkaProducerService} interface
 */
@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    @Override
    public void sendMessage(String message) {
    }

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
