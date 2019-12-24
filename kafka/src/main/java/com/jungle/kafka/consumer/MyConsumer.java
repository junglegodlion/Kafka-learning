package com.jungle.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class MyConsumer {

    public static void main(String[] args) {

        //1.创建消费者配置信息
        Properties properties = new Properties();

        //2.给配置信息赋值


        //连接的集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.18:9092");

        //开启自动提交(offset)
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);

        //自动提交的延时
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");

        //Key,Value的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

        //消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"jungle");

        //重置消费者的offset
//        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        //创建消费者
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        //订阅主题
        //可以订阅多个主题
//        consumer.subscribe(Arrays.asList("first","second"));
        consumer.subscribe(Collections.singletonList("first"));

        while (true) {
            //获取数据
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);

            //解析并打印consumerRecords
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {

                System.out.println(consumerRecord.key() + "--" + consumerRecord.value());
            }
        }

    }
}
