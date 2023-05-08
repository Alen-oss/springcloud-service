package com.wtgroup.openfeignservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// OpenFeign采用的是接口 + 注解的方式
@FeignClient("nacos-service")
public interface NacosServiceFeignClient {

    // 接口中定义的方法通常与服务提供者的方法定义保持一致，@GetMapping说明消费端以GET方式进行请求的
    @GetMapping("/provider/hi")
    public String sayHello();
}
