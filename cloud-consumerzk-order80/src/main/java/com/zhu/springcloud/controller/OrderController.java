package com.zhu.springcloud.controller;

import com.zhu.springcloud.entity.CommonResult;
import com.zhu.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhuchong
 */
@RestController
@Slf4j
public class OrderController {

    public static final String url ="http://cloud-provider-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo(){
        return restTemplate.getForObject(url + "/payment/zk", String.class);
    }

}
