package com.wtgroup.configservice.controller;

import com.wtgroup.configservice.conf.CustomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomConfig customConfig;

    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String getCustom() {
        return String.format("配置中心的自定义：%s", customConfig.getName());
    }
}
