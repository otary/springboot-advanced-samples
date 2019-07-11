package cn.chenzw.springboot.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic模式
 */
@Configuration
public class TopicRabbitConfig {

    final static String message = "topic.message";
    final static String messages = "topic.messages";

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.message);
    }

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue queueMessages() {
        return new Queue(TopicRabbitConfig.messages);
    }

    /**
     * 创建交换器
     *
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 对列绑定并关联到ROUTINGKEY
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
