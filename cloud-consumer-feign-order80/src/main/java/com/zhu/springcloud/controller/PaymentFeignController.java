package com.zhu.springcloud.controller;

import com.zhu.springcloud.entity.CommonResult;
import com.zhu.springcloud.service.PaymentFeignService;
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
public class PaymentFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;


    @GetMapping(value = "/consumer/payment/get/{id}")
    public  CommonResult create(@PathVariable("id") Long id){
        CommonResult commonResult = paymentFeignService.create(id);
        log.info(commonResult.toString());
        return  commonResult;
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public  String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }


}
