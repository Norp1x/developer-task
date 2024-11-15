package com.search.project.kafka.producer.service.impl;

import com.search.project.kafka.producer.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Norpix on 14.11.2024.
 * Description:
 */
@Slf4j
@Component
public class KafkaProducerServiceImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducerService<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerServiceImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message) {
        log.info("Sending message='{}' to topic='{}'", message, topicName);
        kafkaTemplate.send(topicName, key, message);
    }

    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing Kafka Producer");
            kafkaTemplate.destroy();
        }
    }
}