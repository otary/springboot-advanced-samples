package cn.chenzw.springboot.zookeeper.curator.samples.config;

import cn.chenzw.springboot.zookeeper.curator.samples.client.ZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfig {

    /**
     * 重试策略
     *
     * @return
     */
    @Bean
    public RetryNTimes retryPolicy() {
        return new RetryNTimes(10, 5000);
    }

    /**
     * Curator 客户端
     */
    @Bean
    public CuratorFramework curatorClient() {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181, 127.0.0.1:2182,127.0.0.1:2183", 1000, 5000, retryPolicy());
        curatorFramework.start();
        return curatorFramework;
    }

}
