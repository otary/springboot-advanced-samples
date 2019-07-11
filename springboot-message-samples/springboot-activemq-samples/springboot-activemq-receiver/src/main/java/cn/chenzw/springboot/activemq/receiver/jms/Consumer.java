package cn.chenzw.springboot.activemq.receiver.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "sender.queue")
    @SendTo("receiver.queue")
    public String receiveQueue(String text) {
        System.out.println("------------------- Consumer收到报文: " + text + " ------------------");

        return "返回 message: " + text;
    }
}
