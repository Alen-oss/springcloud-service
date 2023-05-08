package com.wtgroup.openfeignservice.controller;

import com.wtgroup.openfeignservice.feignclient.NacosServiceFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {

    @Autowired
    private NacosServiceFeignClient nacosServiceFeignClient;

    @GetMapping("/getHi")
    public void getHi() {
        String result = nacosServiceFeignClient.sayHello();
        log.info("OpenFeign调用结果：{}", result);
    }
}
