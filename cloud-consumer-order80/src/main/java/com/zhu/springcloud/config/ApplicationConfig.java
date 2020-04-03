package com.zhu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhuchong
 */
@Configuration
public class ApplicationConfig {

    @Bean
    //@LoadBalanced 注解表示启用ribbon的负载均衡，这里注释是为了写自己的负载均衡算法
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }

}
