package cn.chenzw.springboot.kafka.basic.samples;

import cn.chenzw.springboot.kafka.basic.samples.producer.MessageProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaBasicSamplesTests {

    @Autowired
    private MessageProducer messageProducer;

    @Test
    public void sendMessage() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            messageProducer.send("spring-kafka-test", "Hello, Kafka");
        }
        Thread.sleep(1000);
    }
}
