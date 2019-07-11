@echo off

start cmd /k "cd springboot-rabbitmq-sender & mvn test"
start cmd /k "cd springboot-rabbitmq-receiver & mvn spring-boot:run"