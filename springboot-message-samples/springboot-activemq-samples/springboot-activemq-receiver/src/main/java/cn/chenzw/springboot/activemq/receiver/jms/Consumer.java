package cn.chenzw.springboot.activemq.receiver.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "test.queue")
    @SendTo("samples.queue")
    public String receiveQueue(String text) {
        System.out.println("Consumer收到报文: " + text);
        return "return message" + text;
    }
}
