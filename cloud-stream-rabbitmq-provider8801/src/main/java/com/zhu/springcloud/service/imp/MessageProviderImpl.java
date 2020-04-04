package com.zhu.springcloud.service.imp;

import cn.hutool.Hutool;
import cn.hutool.core.lang.UUID;
import com.zhu.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @author zhuchong
 * @create 2020-04-04 9:04
 */
@EnableBinding(Source.class) //定义消息推送管道
public class MessageProviderImpl implements IMessageProvider {

    /**
     * 消息发送的管道，这里变量名称必须是output，不然Source识别不到
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serialId = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serialId).build());
        System.out.println("*****serialId"+serialId);
        return null;
    }
}
