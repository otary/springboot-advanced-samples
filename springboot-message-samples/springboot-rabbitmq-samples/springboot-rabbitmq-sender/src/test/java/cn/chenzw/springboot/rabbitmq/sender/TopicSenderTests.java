package cn.chenzw.springboot.rabbitmq.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TopicSenderTests {

    @Autowired
    TopicSender topicSender;

    @Test
    public void testSend(){
        System.out.println("--------------Topic Send-----------------");

        topicSender.send();
    }

    @Test
    public void testSend01(){
        System.out.println("--------------Topic Send01-----------------");

        topicSender.send01();
    }

    @Test
    public void testSend02(){
        System.out.println("--------------Topic Send02-----------------");

        topicSender.send02();
    }
}
