package com.wtgroup.configservice.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

// 热加载必须要满足的两个选项：被@Configuration和@RefreshScope标注
@RefreshScope
@Configuration
public class CustomConfig {

    @Value("${custom.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
