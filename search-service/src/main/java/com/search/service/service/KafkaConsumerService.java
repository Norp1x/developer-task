package com.search.service.service;

/**
 * Created by Norpix on 24.11.2024.
 * Description: Service interface for managing kafka consumer.
 */
public interface KafkaConsumerService {

    /**
     * Consumes a message from the Kafka default topic.
     */
    void consumeMessage();

    /**
     * Consumes a message from the Kafka message with a specified message.
     *
     * @param message the message for the message
     */
    void consumeMessage(String message);
}
