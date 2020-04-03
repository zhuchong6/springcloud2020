package com.zhu.springcloud.service;

import com.zhu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhuchong
 */
public interface PaymentService {
    /**
     * 正常访问的
     * @param id
     * @return
     */
    String paymentInfoOK(Integer id);


    /**
     * 访问超时
     * @param id
     * @return
     */
    String paymentInfoTimeout(Integer id);

    /**
     * 服务熔断demo,所有配置都在HystrixCommandProperties这个类里面
     * @param id
     * @return
     */
    String paymentCircuitBreaker(Integer id);

}
