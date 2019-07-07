package cn.chenzw.springboot.duubo.client;

import cn.chenzw.springboot.dubbo.api.domain.dto.BillDto;
import cn.chenzw.springboot.dubbo.client.DubboClientApp;
import cn.chenzw.springboot.dubbo.client.dubbo.BillDubboConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DubboClientApp.class)
public class DubboClientTests {


    @Autowired
    BillDubboConsumerService billDubboConsumerService;


    @Test
    public void testGetByIdCard() {
        BillDto billDto = billDubboConsumerService.getByIdCard("123");

        System.out.println(billDto);
    }

}
