package com.jungle.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartitioner implements Partitioner {

    /**
     *
     * @param s topic
     * @param o key
     * @param bytes
     * @param o1 value
     * @param bytes1
     * @param cluster Kafka集群
     * @return
     */
    @Override
    public int partition(String s, Object o, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {

        //这里写实际业务中的业务逻辑

//        //获取topic的partition数量
//        Integer integer = cluster.partitionCountForTopic(s);
//
//        return o.toString().hashCode() % integer;
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
