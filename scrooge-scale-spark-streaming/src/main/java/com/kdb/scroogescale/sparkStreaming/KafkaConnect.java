package com.kdb.scroogescale.sparkStreaming;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class KafkaConnect {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConnect.class.getName());


    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("spark://data-server-2:7077").setAppName("KafkaConnector");
        JavaStreamingContext ssc = new JavaStreamingContext(conf, new Duration(1000));

        Map<String, Object> params = new HashMap<>();
        params.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "data-server-2:9093");
        params.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        params.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        params.put(ConsumerConfig.GROUP_ID_CONFIG, "Scrooge-Scale-Group");
        params.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        List<String> topics = Arrays.asList("Scrooge-Scale");

        JavaDStream<ConsumerRecord<String, String>> ds = KafkaUtils.createDirectStream(ssc,
                LocationStrategies.PreferConsistent(), ConsumerStrategies.<String,String>Subscribe(topics, params));
        JavaDStream<String> ss = ds.map(s -> s.value());

        //hbase config
        Configuration config = HBaseConfiguration.create();
        config.addResource("/home/data/hbase/conf/hbase-site.xml");
        config.addResource("/home/data/hadoop/etc/hadoop/hdfs-site.xml");
        config.addResource("/home/data/hadoop/etc/hadoop/core-site.xml");
        config.set("hbase.master", "data-server-1");
        config.set("zookeeper.znode.parent","/hbase-unsecure");
        config.set("hbase.zookeeper.quorum", "data-server-1,data-server-2,data-server-3");
        config.set("hbase.zookeeper.property.clientPort", "2181");

        //redis config
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig,"data-server-2",6379,1000,null);

        Connection connection = null;


        try {
            connection =  ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf("ScroogeTestInfo");
            Table table = connection.getTable(tableName);

            Jedis jedis = jedisPool.getResource();

            ss.foreachRDD(s -> {
                List<String> list = s.collect();
                for(String sss : list){
                    String[] values = sss.split(",");
                    String keyByMD5 = getMD5(values[0]+values[1]);
                    byte[] rowKey = Bytes.toBytes(keyByMD5);
                    Put put = new Put(rowKey);
                    int totalScore = 0;

                    put.addColumn(Bytes.toBytes("meta"),Bytes.toBytes("regdate"), values[2].getBytes());
                    put.addColumn(Bytes.toBytes("meta"),Bytes.toBytes("isChecked"), Bytes.toBytes("F"));
                    for(int i = 3; i< values.length; i+=3){
                        //1,P,7
                        put.addColumn(Bytes.toBytes("factor"),values[i].getBytes(), values[i+1].getBytes());
                        put.addColumn(Bytes.toBytes("score"),values[i].getBytes(), values[i+2].getBytes());
                        totalScore+=Integer.parseInt(values[i+2]);
                    }
                    jedis.set(keyByMD5, String.valueOf(totalScore));
                    table.put(put);
                    jedis.expire(keyByMD5, 1800);//30분뒤 삭제
                }
            });

            if(jedis!=null) jedis.close();
            jedisPool.close();

            ssc.start();
            ssc.awaitTermination();
        }catch (IOException ioException){
            LOGGER.error("IOException >> "+ioException.getMessage());
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException >> "+e.getMessage());
        }
    }

    public static String getMD5(String str){

        String MD5 = "";

        try{

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(str.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();

            for(int i = 0 ; i < byteData.length ; i++){

                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

            }

            MD5 = sb.toString();



        }catch(NoSuchAlgorithmException e){

            e.printStackTrace();

            MD5 = null;

        }

        return MD5;

    }

}
