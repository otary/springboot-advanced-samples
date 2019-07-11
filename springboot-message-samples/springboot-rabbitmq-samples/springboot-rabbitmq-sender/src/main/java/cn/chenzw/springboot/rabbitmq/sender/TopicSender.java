package cn.chenzw.springboot.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "hi, i am message all";
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", message);
    }

    public void send01() {
        String message = "hi, i am message 1";
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", message);
    }

    public void send02() {
        String message = "hi, i am messages 2";
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", message);
    }
}
