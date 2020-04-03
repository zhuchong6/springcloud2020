package com.zhu.springcloud.service;

import com.zhu.springcloud.entity.CommonResult;
import com.zhu.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author zhuchong
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-SERVICE")
public interface PaymentFeignService {
    /**
     * 查找
     * @param id
     * @return
     */
    @GetMapping("/payment/get/{id}")
    CommonResult create(@PathVariable("id") Long id);

    /**
     * 测试延迟接口
     * @return
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();

}
