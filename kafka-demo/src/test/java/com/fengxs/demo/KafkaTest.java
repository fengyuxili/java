package com.fengxs.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

public class KafkaTest  extends KafkaDemoApplicationTests{

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired

    @Test
    public void send() {
        kafkaTemplate.send("test", "key", "hello world!");
    }

    public static void main(String[] args) {
        ThreadLocal num = new ThreadLocal();
    }
}
