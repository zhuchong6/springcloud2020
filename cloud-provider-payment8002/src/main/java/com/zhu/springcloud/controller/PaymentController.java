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

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("***插入***: "+String.valueOf(payment));
        int age =10;
        if(i>0){
            return  new CommonResult(200, "插入数据库成功,serverPort:"+serverPort, i);
        }else{
            return  new CommonResult(444, "插入数据库失败,serverPort:"+serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult create(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***插入***: "+payment);
        if(payment != null){
            return  new CommonResult(200, "查询成功,serverPort:"+serverPort, payment);
        }else{
            return  new CommonResult(444, "没有对应记录，查询ID,serverPort:"+serverPort+id);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s :services) {
            log.info("*****"+s+"*****");
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance instance : instances) {
            String serviceId = instance.getServiceId();
            String host = instance.getHost();
            int port = instance.getPort();
            Object usi = instance.getUri();

            log.info("serviceId="+serviceId+"\thost="+host+"\tport="+port+"\turi="+usi);
        }
        return discoveryClient;
    }


    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String zipkin(){
        return "zipkin调用测试";
    }
}
