# springboot-message-samples

- springboot-activemq-samples

## 核心概念

- 消息代理（message broker）：消息代理保证消息传递到指定的目的地、
  - JSM（Java Message Service）：Java消息服务，基于JVM消息代理的规范
    - ActiveMQ
    - HornetQ
    - Artemis
  - AMQP（Advanced Message Queuing Protocol）：兼容JSM，还支持跨语言和平台
- 目的地（destination）：常见两种形式
  - 队列（queue）：点对点（point-to-point）的消息通讯 - 每一条消息只有唯一一个接收者和发送者
  - 主题（topic）：发布/订阅式（publish/subscribe）的消息通讯
 