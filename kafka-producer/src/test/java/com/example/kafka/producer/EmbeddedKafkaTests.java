package com.example.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmbeddedKafkaTests.class)
@EmbeddedKafka(count = 4, ports = {9092,9093,9094,9095}, topics = {"test"})
public class EmbeddedKafkaTests {
    @Test
    public void contextLoads()throws IOException {
        System.in.read();
    }
}