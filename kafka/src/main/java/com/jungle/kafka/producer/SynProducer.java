package com.jungle.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SynProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.创建Kafka生产者的配置信息
        Properties properties = new Properties();

        //2.kafka 集群， broker-list
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.18:9092");
        properties.put("bootstrap.servers", "192.168.1.18:9092");

        //3.ACK应答级别
        properties.put("acks", "all");

        //4.重试次数
        properties.put("retries", 3);

        //5.批次大小
        //一次发送的大小
        //达到16k后发送
        properties.put("batch.size", 16384);

        //6.等待时间
        //1ms后发送
        properties.put("linger.ms", 1);

        //7.RecordAccumulator 缓冲区大小
        properties.put("buffer.memory", 33554432);


        //8.Key,Value的序列化类
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        //9.创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //10.发送数据
        for (int i = 0; i < 10; i++) {

            Future<RecordMetadata> first = producer.send(new ProducerRecord<>("first", "jungle--" + i));

            RecordMetadata recordMetadata = first.get();

        }


        //11.关闭资源
        producer.close();
    }
}
