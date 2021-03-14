package com.kdb.scroogescaleweb.dao;

public interface KafkaProducerDAO {
    public boolean sendMessage(String message);
}
