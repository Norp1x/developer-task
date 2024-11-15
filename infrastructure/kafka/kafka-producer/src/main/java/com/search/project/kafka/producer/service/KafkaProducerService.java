package com.search.project.kafka.producer.service;

import java.io.Serializable;

/**
 * Created by Norpix on 14.11.2024.
 * Description:
 */
public interface KafkaProducerService<K extends Serializable, V extends String> {

        void send(String topicName, K key, V message);
}
