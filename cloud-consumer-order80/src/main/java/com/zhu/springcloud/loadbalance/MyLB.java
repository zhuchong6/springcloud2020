package com.zhu.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡实现类，主要实现轮询算法
 * @author zhuchong
 */
@Component
public class MyLB implements LoadBalance {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 计算访问次数，默认1开始，自旋锁实现
     * @return
     */
    public final int getAndIncrement(){
        int current;//当前的次数
        int next; //下一次

        do{
            current = this.atomicInteger.get();
            next =  current>Integer.MAX_VALUE ? 0: current+1;
        }while( !this.atomicInteger.compareAndSet(current,next));
        System.out.println("****第"+next+"次访问****");
        return next;
    }

    @Override
    public ServiceInstance loads(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
