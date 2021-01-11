package cn.chenzw.springboot.kafka.basic.samples.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Slf4j
@Component
public class MessageConsumer implements MessageListener<String, String> {

    /**
     * 消费组监听消息
     *
     * @param data
     */
    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        log.info("监听到消息: topic={}, value={}", data.topic(), data.value());
    }
}
