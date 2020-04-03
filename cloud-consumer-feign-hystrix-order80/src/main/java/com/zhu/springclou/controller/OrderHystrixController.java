package com.zhu.springclou.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhu.springclou.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhuchong
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfoTimeoutHandlerDefault")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id){
        String payment = paymentHystrixService.paymentInfoOK(id);
        return payment;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
/*    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })*/
    @HystrixCommand
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String payment = paymentHystrixService.paymentInfoTimeout(id);
        return payment;
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错，请检查自己，/(ㄒoㄒ)/~~";
    }

    //全局服务降级处理类，没有指定的，默认使用这个分
    public String paymentInfoTimeoutHandlerDefault(){
        return "我是全局通用方法消费者80，对方支付系统繁忙请10秒钟后再试或者自己运行出错，请检查自己，/(ㄒoㄒ)/~~";
    }


}
