@echo off

@rem set ERLANG_HOME=C:\DPS\erl10.4

start cmd /c %~dp0%\rabbitmq_server-3.7.16\sbin\rabbitmq-plugins.bat enable rabbitmq_management

%~dp0%\rabbitmq_server-3.7.16\sbin\rabbitmq-service.bat start