package com.chxp;

import com.chxp.cfgbeans.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @SpringBootApplication -> @ComponentScan
 * 自定义 Ribbon 配置类 MySelfRule 不能放在 @ComponentScan 所扫描的当前包下以及子包下，
 * 否则自定义的这个配置类就会被所有的 Ribbon 客户端所共享，也就达不到特殊化定制的目的了。
 */
@SpringBootApplication
@EnableEurekaClient
/**
 * 启动 MICROSERVICECLOUD-DEPT 微服务时加载自定义 Ribbon 配置类 MySelfRule 从而 使配置生效
 * @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
 */
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_App.class,args);
    }
}
