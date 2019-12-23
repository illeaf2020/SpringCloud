package com.chxp.controller;

import com.chxp.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    //    private static final String REST_URL_PREFIX = "http://localhost:8001";
    // 实现微服务消费者请求微服务生产者
    // Ribbon 和 Eureka 整合后 Consumer 可以直接调用服务而不用再关心地址和端口号
    // 通过微服务名字从 Eureka 上找到并访问
    private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";

    /**
     * (url,requestMap,ResponseBean.class) 
     * (REST 请求地址,请求参数,HTTP 响应转换被转换成的对象类型)
     * <p>
     * Spring Cloud Ribbon 是基于Netflix Ribbon 实现的一套 客户端 负载均衡 的工具
     * 实现客户端通过 RestTemplate 访问微服务时自带负载均衡
     */
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        Boolean b = restTemplate.patchForObject(REST_URL_PREFIX + "/dept/add", dept, boolean.class);
        return b;
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        Dept object = restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
        return object;
    }

    @SuppressWarnings("unchecked") //  告知编译器忽略 unchecked 警告信息
    @GetMapping("/consumer/dept/list")
    public List<Dept> list() {
        List<Dept> objects = restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
        return objects;
    }

    // 测试 @EnableDiscoveryClient,消费端可以调用服务发现
    @RequestMapping(value = "/consumer/dept/discovery")
    public Object discovery() {
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
    }
}
