package com.zhu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhu.springcloud.dao.PaymentDao;
import com.zhu.springcloud.entity.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhuchong
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    @Override

    public String paymentInfoOK(Integer id) {
        return "线程池： "+Thread.currentThread().getName()+" paymentInfo ok, id: "+id +"😄";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfoTimeout(Integer id) {
        //int age=10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： "+Thread.currentThread().getName()+" id: "+id +"😄 \t耗时(秒)： ";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口或时间范围
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//失败率达到多少后跳闸（熔断）
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id<0){
            throw  new RuntimeException("*****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentInfoTimeoutHandler(Integer id){
        return "线程池： "+Thread.currentThread().getName()+" 8001系统繁忙或者运行报错请稍后再试, id: "+id +"\t /(ㄒoㄒ)/~~";
    }

    public String paymentCircuitBreakerHandler(Integer id){
        return  "id 不能为负数，请稍后再试！！！  id: "+ id;
    }

}
