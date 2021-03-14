package com.kdb.scroogescaleweb.dao;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scala.Tuple2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import static org.apache.spark.sql.functions.avg;
import static org.apache.spark.sql.functions.stddev;
@Repository
public class ResultDAOImpl implements ResultDAO{
    @Autowired
    private SparkSession sparkSession;
    @Autowired
    private JavaSparkContext sc;
    @Autowired
    private Configuration conf;


    @Override
    public Map<String, Double> selectStat() {
        Map<String, Double> map = new HashMap<>();
        JavaPairRDD<ImmutableBytesWritable, Result> javaPairRDD =
                sc.newAPIHadoopRDD(conf, TableInputFormat.class, ImmutableBytesWritable.class, Result.class);

        JavaRDD<Integer> javaRDD = javaPairRDD.map(new ScanConvertFunction());
        Dataset<Integer> ds = sparkSession.createDataset(javaRDD.rdd(), Encoders.INT());
        ds.show();
        Dataset<Row> statDs = ds.select(avg("value").alias("avg"), stddev("value").alias("std"));
        statDs.show();
        List<Row> list = statDs.collectAsList();
        for(Row r : list){
            System.out.println("0 >> "+r.get(0));
            System.out.println("1 >> "+r.get(1));
        }
        statDs.collectAsList().forEach(row -> {
            Double avg = row.getAs("avg");
            Double std = row.getAs("std");
            map.put("avg",avg);
            map.put("std",std);
        });

//        System.out.println(map.entrySet().stream().map(e -> e.getKey() +"->"+ e.getValue())
//                .collect(Collectors.joining(", ", "[ ", " ]")));

        return map;
    }
    private static class ScanConvertFunction implements
             Function<Tuple2<ImmutableBytesWritable, Result>, Integer> {
        @Override
        public Integer call(Tuple2<ImmutableBytesWritable, Result> v1) throws Exception {
            Result result = v1._2;
//            String rowKey = Bytes.toString(result.getRow());
            int totalScore= 0;
            NavigableMap<byte[],byte[]> map = result.getFamilyMap(Bytes.toBytes("score"));

            for( Map.Entry<byte[],byte[]> entry :map.entrySet()){
                String str = Bytes.toString(entry.getValue());
                int value =  Integer.valueOf(str);
                totalScore+=value;
            }

                return  totalScore;
        }
    }

}
