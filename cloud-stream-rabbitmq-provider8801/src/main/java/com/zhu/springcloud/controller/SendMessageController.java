package com.zhu.springcloud.controller;

import com.zhu.springcloud.service.imp.MessageProviderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述:
 * 发送消息
 *
 * @author zhuchong
 * @create 2020-04-04 9:15
 */
@RestController
public class SendMessageController {

    @Resource
    private MessageProviderImpl messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }

}
