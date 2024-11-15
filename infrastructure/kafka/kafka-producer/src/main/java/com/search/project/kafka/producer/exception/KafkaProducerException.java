package com.search.project.kafka.producer.exception;

/**
 * Created by Norpix on 14.11.2024.
 * Description: Default runtime exception class for Kafka Producer
 */
public class KafkaProducerException extends RuntimeException {

    public KafkaProducerException(String message) {
        super(message);
    }
}
