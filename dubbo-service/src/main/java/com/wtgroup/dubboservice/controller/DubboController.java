package com.wtgroup.dubboservice.controller;

import com.wtgroup.facade.FacadeInterface;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/dubbo")
public class DubboController {

    @DubboReference
    private FacadeInterface facadeInterface;

    @GetMapping("/getScore/{name}")
    public void dubboService(@PathVariable("name") String name) {

        String result = facadeInterface.getScore(name);
        log.info("Dubbo服务调用返回结果：{}", result);
    }
}
