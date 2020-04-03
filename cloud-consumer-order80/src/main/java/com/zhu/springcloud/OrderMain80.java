package com.zhu.springcloud;

import com.zhu.ribbon.MyRuler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author zhuchong
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="CLOUD-PROVIDER-SERVICE", configuration= MyRuler.class)//启用自定义ribbon规则
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
