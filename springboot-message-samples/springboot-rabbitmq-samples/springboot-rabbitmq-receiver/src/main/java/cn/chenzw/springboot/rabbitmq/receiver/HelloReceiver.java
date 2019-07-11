package cn.chenzw.springboot.rabbitmq.receiver;

import cn.chenzw.springboot.rabbitmq.domain.dto.SysUserDto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"hello", "object"})
public class HelloReceiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("--------------------Receiver : " + message + "------------------");
    }

    @RabbitHandler
    public void process(SysUserDto sysUserDto) {
        System.out.println("--------------------Receiver : " + sysUserDto + "------------------");
    }
}
