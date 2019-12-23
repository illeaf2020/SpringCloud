package com.chxp.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Configuration ConfigBean <---> applicationContext.xml
 */
@Configuration
public class ConfigBean {

    /**
     * Spring Cloud Ribbon 是基于Netflix Ribbon 实现的一套 客户端 负载均衡 的工具
     * @LoadBalanced -> 实现客户端通过 RestTemplate 访问微服务时自带负载均衡(默认算法：轮询算法)
     * 对 ConfigBean 进行新注解 @LoadBalanced，在获得 Rest 时加入 Ribbon 的配置
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
