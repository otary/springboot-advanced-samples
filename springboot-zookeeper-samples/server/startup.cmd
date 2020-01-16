@echo off

start cmd /k "%~dp0/zookeeper/bin/zkServer.cmd"
start cmd /k "%~dp0/zookeeper2/bin/zkServer.cmd"
start cmd /k "%~dp0/zookeeper3/bin/zkServer.cmd"

exit
