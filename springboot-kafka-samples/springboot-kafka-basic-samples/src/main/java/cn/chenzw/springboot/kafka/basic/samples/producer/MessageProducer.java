package cn.chenzw.springboot.kafka.basic.samples.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * 发送消息
     *
     * @param topic
     * @param value
     */
    public void send(String topic, String value) {
        kafkaTemplate.send(topic, value);
    }
}
