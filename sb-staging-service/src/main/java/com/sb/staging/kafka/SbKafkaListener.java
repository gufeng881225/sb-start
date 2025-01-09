package com.sb.staging.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SbKafkaListener<K,V> {

    //@KafkaListener(topics = {"topic1","topic2"}, groupId = "${sb.message.group.groupId-1}")
    @KafkaListener(topics = "#{kafkaTopicConfig.getKafkaTopics()}", groupId = "${sb.message.group.groupId-1}")
    public void consumerMsg(ConsumerRecord<K, V> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            System.out.println(record.topic());
            System.out.println(message);
        }
    }
}