package cn.chenzw.springboot.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQSenderApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQSenderApp.class, args);
    }
}
