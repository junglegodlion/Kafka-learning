package com.jungle.kafka.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class CallBackProducer {

    public static void main(String[] args) {

        //1.创建配置信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.18:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");


        //2.创建生产者对象
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        //3.发送数据
        for (int i = 0; i < 10; i++) {

            //producer.send(new ProducerRecord<>("first", "jungle--" + i), new Callback() {
            producer.send(new ProducerRecord<>("first", "jungle--" + i), (recordMetadata, e) -> {
                if (e == null) {
                    System.out.println((recordMetadata.partition() + "--" + recordMetadata.offset()));
                } else {
                    e.printStackTrace();
                }
            });
        }

        //4.关闭资源
        producer.close();
    }

}
