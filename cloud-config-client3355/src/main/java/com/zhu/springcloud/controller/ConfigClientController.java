package com.zhu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 测试rest风格读取配置中心
 *
 * @author zhuchong
 * @create 2020-04-04 6:19
 */
@RestController
@RefreshScope//加了注解后，必须要执行post请求才能实现刷新 curl -X POST "http://localhost:3355/actuator/refresh"
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
