package cn.chenzw.springboot.kafka.basic.samples.config;

import cn.chenzw.springboot.kafka.basic.samples.consumer.MessageConsumer;
import cn.chenzw.springboot.kafka.basic.samples.producer.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import java.util.HashMap;

@Configuration
public class KafkaConfig {

    /**
     * 生产者工厂
     *
     * @return
     */
    @Bean
    public DefaultKafkaProducerFactory kafkaProducerFactory() {
        return new DefaultKafkaProducerFactory(new HashMap<String, String>() {
            {
                // kafka集群地址
                put("bootstrap.servers", "127.0.0.1:9092");
                put("retries", "1");
                put("batch.size", "16384");
                put("buffer.memory", "10285760");
                put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            }
        });
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate(kafkaProducerFactory(), true);
    }

    /**
     * 消费者工厂
     *
     * @return
     */
    @Bean
    public DefaultKafkaConsumerFactory kafkaConsumerFactory() {

        return new DefaultKafkaConsumerFactory(new HashMap<String, String>() {
            {
                put("bootstrap.servers", "127.0.0.1:9092");
                put("group.id", "kafka_consumer_group");
                put("session.timeout.ms", "30000");
                put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            }
        });
    }


    @Bean
    public ContainerProperties containerProperties() {
        ContainerProperties containerProperties = new ContainerProperties("spring-kafka-test");
        // containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL);
        containerProperties.setMessageListener(new MessageConsumer());
        containerProperties.setGroupId("spring-kafka-group");
        return containerProperties;
    }

    @Bean
    public KafkaMessageListenerContainer kafkaMessageListenerContainer() {
        KafkaMessageListenerContainer kafkaMessageListenerContainer = new KafkaMessageListenerContainer(kafkaConsumerFactory(), containerProperties());
        kafkaMessageListenerContainer.start();
        return kafkaMessageListenerContainer;
    }
}
