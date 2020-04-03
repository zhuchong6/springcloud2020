package com.zhu.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author zhuchong
 */
public interface LoadBalance {
    /**
     * 传入所有服务节点，通过负载均衡选一个
     * @param serviceInstances 所有服务节点
     * @return
     */
    ServiceInstance loads(List<ServiceInstance> serviceInstances);
}
