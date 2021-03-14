package com.kdb.scroogescaleweb.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {
    @Value("${spark.app.name}")
    private String appName;
    @Value("${spark.master}")
    private String masterUri;

    @Bean
    public SparkConf sparkConf(){
        return new SparkConf().setAppName(appName)
                .set("spark.master",masterUri);
    }

    @Bean
    public JavaSparkContext sc(){
        return new JavaSparkContext(sparkSession().sparkContext());
    }
    @Bean
    public SparkSession sparkSession(){
        return SparkSession.builder()
                .appName(appName)
                .master(masterUri)
                .getOrCreate();
    }
}
