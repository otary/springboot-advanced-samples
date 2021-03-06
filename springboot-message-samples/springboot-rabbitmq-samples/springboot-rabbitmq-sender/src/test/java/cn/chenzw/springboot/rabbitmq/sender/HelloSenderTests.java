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
        System.out.println("-----------发送字符串-------------");

        helloSender.send();
    }

    @Test
    public void testSendObject(){
        System.out.println("-----------发送Object对象-------------");

        helloSender.sendObject();
    }
}
