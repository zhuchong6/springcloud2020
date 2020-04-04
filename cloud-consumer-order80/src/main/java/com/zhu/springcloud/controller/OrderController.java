package com.zhu.springcloud.controller;

import com.zhu.springcloud.entity.CommonResult;
import com.zhu.springcloud.entity.Payment;
import com.zhu.springcloud.loadbalance.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author zhuchong
 */
@RestController
@Slf4j
public class OrderController {

    public static final String url ="http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalance loadBalance;




    @GetMapping("consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(url + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
       return restTemplate.getForObject(url + "/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(url + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }

    }


    @GetMapping(value = "/consumer/payment/lb")
    public String lb(){

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");

        if(instances == null || instances.size()<0){
            return null;
        }
        ServiceInstance loads = loadBalance.loads(instances);
        URI uri = loads.getUri();
        log.info(uri.toString());
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    /**
     * zipkin + sleuth调用测试
     * @return
     */
    @GetMapping(value = "/consumer/payment/zipkin")
    public String zipkin(){
        return restTemplate.getForObject( "http://localhost:8002/payment/zipkin", String.class);
    }


}
