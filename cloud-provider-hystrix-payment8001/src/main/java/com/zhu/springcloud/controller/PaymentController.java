package com.zhu.springcloud.controller;

import com.zhu.springcloud.entity.CommonResult;
import com.zhu.springcloud.entity.Payment;
import com.zhu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuchong
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;



    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id){
        String payment = paymentService.paymentInfoOK(id);
        log.info("*****处理成功："+payment+"*****");
        return payment;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String payment = paymentService.paymentInfoTimeout(id);
        log.info("*****处理成功："+payment+"*****");
        return payment;
    }

    /**
     * 服务熔断测试
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String payment = paymentService.paymentCircuitBreaker(id);
        log.info("*****处理成功："+payment+"*****");
        return payment;
    }
}
