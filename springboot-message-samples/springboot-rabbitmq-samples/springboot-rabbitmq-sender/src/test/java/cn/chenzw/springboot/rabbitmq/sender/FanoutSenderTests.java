package cn.chenzw.springboot.rabbitmq.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FanoutSenderTests {

    @Autowired
    FanoutSender fanoutSender;

    @Test
    public void testSend(){
        System.out.println("---------------Fanout Send--------------------");

        fanoutSender.send();
    }

}
