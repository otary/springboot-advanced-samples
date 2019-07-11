@echo off

@rem start server\startServer.cmd
start cmd /k "cd springboot-rabbitmq-sender & mvn test -e"
start cmd /k "cd springboot-rabbitmq-receiver & mvn spring-boot:run"

TIMEOUT /T 30 /NOBREAK

start chrome.exe http://127.0.0.1:15672