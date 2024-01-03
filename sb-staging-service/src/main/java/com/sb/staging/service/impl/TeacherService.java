package com.sb.staging.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Value("${c2g.export.kafka.consumer.topic.pf.execute:localhost:9094}")
    private String executeExportTopic;

    public void print() {
        System.out.println(executeExportTopic);
    }
}