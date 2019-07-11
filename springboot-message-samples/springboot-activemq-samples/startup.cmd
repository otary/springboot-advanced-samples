@echo off

start %~dp0%\server\startServer.cmd
start cmd /k "cd springboot-activemq-receiver & mvn spring-boot:run"
start cmd /k "cd springboot-activemq-sender & mvn spring-boot:run"

TIMEOUT /T 30 /NOBREAK

start chrome.exe http://127.0.0.1:8161/admin