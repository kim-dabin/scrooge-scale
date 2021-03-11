package com.kdb.scroogescale.dao;

import com.kdb.scroogescale.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KafkaProducerDAO {
    private static final String TOPIC="Scrooge-Scale";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerDAO.class.getName());
    @Autowired
    public KafkaProducerDAO(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean sendMessage(String message){
        try {
            LOGGER.info(String.format("Produce message : %s", message));
            kafkaTemplate.send(TOPIC,message);
            kafkaTemplate.flush();
            //transaction
            return true;
        }catch (Exception e){
            LOGGER.error(String.format("Produce Exception : %s", e.getMessage()));
            return false;
        }
    }
}
