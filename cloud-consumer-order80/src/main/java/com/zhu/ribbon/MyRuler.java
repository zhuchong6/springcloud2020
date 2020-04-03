package com.zhu.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 替换当前客户端ribbon负载均衡策略，注意包要在SpringBoot启动类上次，不能再它同一或者下层目录
 * @author zhuchong
 */
@Configuration
public class MyRuler {

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}
