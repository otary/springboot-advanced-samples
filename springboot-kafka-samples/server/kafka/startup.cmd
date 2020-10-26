@rem @echo off

start cmd /k "%~dp0/bin/windows/zookeeper-server-start.bat %~dp0/config/zookeeper.properties"

start cmd /k "%~dp0/bin/windows/kafka-server-start.bat %~dp0/config/server.properties"

exit