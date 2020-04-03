package com.zhu.springcloud.service;

import com.zhu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhuchong
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
