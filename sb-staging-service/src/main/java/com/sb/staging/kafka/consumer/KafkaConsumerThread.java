package com.sb.staging.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.*;

public class KafkaConsumerThread extends Thread {
    private KafkaConsumer<String, String> kafkaConsumer;
    private ExecutorService executorService;


    public KafkaConsumerThread(String topic, Properties properties) {
        kafkaConsumer = new KafkaConsumer(properties);
        kafkaConsumer.subscribe(Collections.singletonList(topic));
        //Executors.newCachedThreadPool();
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(300), new ThreadPoolExecutor.CallerRunsPolicy());

    }

    @Override
    public void run() {
        //  拉取线程
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
                if (!records.isEmpty()) {
                    executorService.submit(new RecordsHandler(records));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
            //executorService.shutdown();
        }
    }
}