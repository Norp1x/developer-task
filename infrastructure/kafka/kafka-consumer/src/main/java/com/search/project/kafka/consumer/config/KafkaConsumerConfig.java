package com.search.project.kafka.consumer.config;

import com.search.project.kafka.config.data.KafkaConfigData;
import com.search.project.kafka.config.data.KafkaConsumerConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Norpix on 14.11.2024.
 * Description: Configuration class for Kafka Producer module
 */
@Configuration
public class KafkaConsumerConfig<K extends Serializable, V extends SpecificRecordBase> {

    private final KafkaConfigData kafkaConfigData;
    private final KafkaConsumerConfigData kafkaConsumerConfigData;

    public KafkaConsumerConfig(KafkaConfigData kafkaConfigData, KafkaConsumerConfigData kafkaConsumerConfigData) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaConsumerConfigData = kafkaConsumerConfigData;
    }

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfigData.getBootstrapServers());
        properties.put(kafkaConfigData.getSchemaRegistryUrlKey(), kafkaConfigData.getSchemaRegistryUrl());
        properties.put(kafkaConsumerConfigData.getSpecificAvroReaderKey(), kafkaConsumerConfigData.getSpecificAvroReader());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigData.getKeyDeserializer());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfigData.getValueDeserializer());
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConsumerConfigData.getSessionTimeoutMs());
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConsumerConfigData.getHeartbeatIntervalMs());
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaConsumerConfigData.getMaxPollIntervalMs());
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerConfigData.getMaxPollRecords());
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, kafkaConsumerConfigData.getMaxPartitionFetchBytesDefault()
                * kafkaConsumerConfigData.getMaxPartitionFetchBytesBoostFactor());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerConfigData.getAutoOffsetReset());
        return properties;
    }

    @Bean
    public ConsumerFactory<K, V> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setBatchListener(kafkaConsumerConfigData.getBatchListener());
        factory.setConcurrency(kafkaConsumerConfigData.getConcurrencyLevel());
        factory.setAutoStartup(kafkaConsumerConfigData.getAutoStartup());
        factory.getContainerProperties().setPollTimeout(kafkaConsumerConfigData.getPollTimeoutMs());
        return factory;
    }
}
