package cn.chenzw.springboot.rabbitmq.receiver.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收到所有信息
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver02 {

    @RabbitHandler
    public void process(String message) {
        System.out.println("----------------------Topic Receiver02 : " + message + "--------------------");
    }
}
