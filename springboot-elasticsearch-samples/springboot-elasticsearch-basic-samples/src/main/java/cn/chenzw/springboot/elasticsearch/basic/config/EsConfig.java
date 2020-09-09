package cn.chenzw.springboot.elasticsearch.basic.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.7.177", 9200, "http")
                        // ,new HttpHost("192.168.17.190", 9200, "http")
                )
                        // 设置http客户请求时长
                        .setMaxRetryTimeoutMillis(60 * 1000));
        return client;
    }

}
