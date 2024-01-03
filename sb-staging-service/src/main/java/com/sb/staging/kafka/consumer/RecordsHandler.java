package com.sb.staging.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

public class RecordsHandler extends Thread {
    public final ConsumerRecords<String, String> records;

    public RecordsHandler(ConsumerRecords<String, String> records) {
        this.records = records;
    }

    @Override
    public void run() {
        //工作线程
        System.out.println(Thread.currentThread().getName());
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.value());
        }
    }
}
