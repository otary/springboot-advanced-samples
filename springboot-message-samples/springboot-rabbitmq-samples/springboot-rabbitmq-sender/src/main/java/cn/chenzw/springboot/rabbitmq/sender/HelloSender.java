package cn.chenzw.springboot.rabbitmq.sender;

import cn.chenzw.springboot.rabbitmq.domain.dto.SysUserDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    /**
     * 发送字符串示例
     */
    public void send() {
        String message = "hello " + new Date();
        this.rabbitTemplate.convertAndSend("hello", message);
    }

    /**
     * 发送Object对象示例
     */
    public void sendObject() {
        SysUserDto sysUserDto = new SysUserDto();
        sysUserDto.setId(1L);
        sysUserDto.setName("张三");
        sysUserDto.setBirth(new Date());
        this.rabbitTemplate.convertAndSend("object", sysUserDto);
    }

}
