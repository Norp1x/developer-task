package com.search.project.kafka.consumer.service;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

/**
 * Created by Norpix on 14.11.2024.
 * Description:
 */
public interface KafkaConsumerService<T extends SpecificRecordBase> {

    void receive(List<T> messages, List<Long> keys, List<Integer> partitions, List<Long> offsets);
}
