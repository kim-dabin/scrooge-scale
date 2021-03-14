package com.kdb.scroogescaleweb.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HbaseConfig {


    @Value("${spring.data.hbase.zkQuorum}")
    private String zkQuorum;
    @Value("${spring.data.hbase.zkPort}")
    private String zkPort;
    @Value("${spring.data.hbase.master}")
    private String master;
    @Value("${spring.data.hbase.zookeeper.znode.parent}")
    private String znodeParent;

    private static final String TABLE="ScroogeTestInfo";


    @Bean
    public Configuration conf(){
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", zkQuorum);
        configuration.set("hbase.master", master);
        configuration.set("hbase.zookeeper.property.clientPort", zkPort);
        configuration.set(TableInputFormat.INPUT_TABLE, TABLE);
        configuration.set("zookeeper.znode.parent",znodeParent);
        configuration.addResource("/home/data/hbase/conf/hbase-site.xml");
        configuration.addResource("/home/data/hadoop/etc/hadoop/hdfs-site.xml");
        configuration.addResource("/home/data/hadoop/etc/hadoop/core-site.xml");
        return configuration;
    }
//    @Bean
//    public HbaseTemplate hbaseTemplate(){
//        return new HbaseTemplate(conf());
//    }


}
