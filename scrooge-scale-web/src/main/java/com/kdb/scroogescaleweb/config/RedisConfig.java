package com.kdb.scroogescaleweb.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig(){
        return new GenericObjectPoolConfig();
    }

    @Bean public JedisPool jedisPool(){
        return new JedisPool(genericObjectPoolConfig(), host, port);
    }
}
