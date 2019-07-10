package cn.chenzw.springboot.activemq.sender.jms;

import cn.chenzw.springboot.activemq.sender.ActiveMQSenderApp;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActiveMQSenderApp.class)
public class ProducerTests {

    @Autowired
    private Producer producer;

    @Test
    public void testSendMessage() throws InterruptedException {
        Destination destination = new ActiveMQQueue("test.queue");

        for (int i = 0; i < 100; i++) {
            producer.sendMessage(destination, "hello, my name is zhagnsan");
        }
    }
}
