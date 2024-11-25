package com.google.service.service;

/**
 * Created by Norpix on 24.11.2024.
 * Description: Service interface for managing kafka producer.
 */
public interface KafkaProducerService {

    /**
     * Sends a message to the Kafka default topic.
     *
     * @param message the message to be sent
     */
    void sendMessage(String message);

    /**
     * Sends a message to the Kafka topic with a specified topic.
     *
     * @param topic the topic for the message
     * @param message the message to be sent
     */
    void sendMessage(String topic, String message);
}
