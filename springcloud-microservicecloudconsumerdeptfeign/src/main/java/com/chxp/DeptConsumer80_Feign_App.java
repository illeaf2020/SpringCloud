package com.chxp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableFeignClients(basePackages= {"com.chxp"})
 * 启用 Feign 客户端，从外部引入的其他服务 jar 包里标注了 @FeignClient 注解的 interface 自动生成 bean 对象
 */
//@EnableFeignClients(basePackages= {"com.chxp"})
//@ComponentScan("com.chxp")

@EnableFeignClients // ???

@SpringBootApplication
@EnableEurekaClient
public class DeptConsumer80_Feign_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_Feign_App.class,args);
    }
}
