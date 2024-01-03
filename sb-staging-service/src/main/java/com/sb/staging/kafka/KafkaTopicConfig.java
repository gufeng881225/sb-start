package com.sb.staging.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ConfigurationProperties("sb.message")
@Component
@Data
public class KafkaTopicConfig {
    private String topic;
    private List<String> topics = new ArrayList();

    public String[] getKafkaTopics() {
        List<String> kafkaTopics = getTopics(topic);
        for (String t : topics) {
            List<String> list = getTopics(t);
            kafkaTopics.addAll(list);
        }
        return kafkaTopics.toArray(new String[0]);
    }

    private List<String> getTopics(String topicStr) {
        List<String> kafkaTopics = new ArrayList<>();
        if (StringUtils.hasText(topicStr)) {
            String[] arr = topicStr.split(",");
            kafkaTopics.addAll(Arrays.stream(arr).map(String::trim).filter(StringUtils::hasText).collect(Collectors.toList()));
        }
        return kafkaTopics;
    }
}
