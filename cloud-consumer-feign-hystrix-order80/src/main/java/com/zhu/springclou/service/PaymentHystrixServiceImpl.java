package com.zhu.springclou.service;

import org.springframework.stereotype.Component;

/**
 * @author zhuchong
 */
@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfoTimeout(Integer id) {
        return "---PaymentHystrixServiceImpl fall back paymentInfoTimeout , /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfoOK(Integer id) {
        return "---PaymentHystrixServiceImpl fall back paymentInfoOK , /(ㄒoㄒ)/~~";
    }
}
