package com.zhu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述:
 * StreanRabbitMQ消息发送者测试
 *
 * @author zhuchong
 * @create 2020-04-04 9:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StreamRabbitMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitMQMain8801.class, args);
    }
}
