package cn.chenzw.springboot.rabbitmq.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloSenderTests {

    @Autowired
    HelloSender helloSender;

    @Test
    public void testSend(){
        helloSender.send();
    }

    @Test
    public void testSendObject(){
        helloSender.sendObject();
    }
}
