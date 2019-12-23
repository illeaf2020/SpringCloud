package com.chxp.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {

    /**
     * 显示指定访问微服务算法(指定算法API)
     */
    @Bean
    public IRule myRule() {

//		return new RandomRule(); // Ribbon 默认是轮询，手动定义为随机

        return new RandomRule_ZY();// 自定义算法
    }
}
