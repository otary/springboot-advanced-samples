package cn.chenzw.springboot.rabbitmq.receiver.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver01 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("----------------------Topic Receiver01 : " + message + "--------------------");
    }
}
