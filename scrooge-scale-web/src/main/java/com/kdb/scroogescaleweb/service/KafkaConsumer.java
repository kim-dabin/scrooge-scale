package com.kdb.scroogescaleweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class.getName());

    @KafkaListener(topics = "Scrooge-Scale", groupId = "Scrooge-Scale-Group")
    public void consume(String message) throws IOException {
        LOGGER.info(String.format("Consumed message : %s", message));
    }
}
