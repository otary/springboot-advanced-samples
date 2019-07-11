package cn.chenzw.springboot.rabbitmq.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMQReceiverApp {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQReceiverApp.class, args);
    }
}
