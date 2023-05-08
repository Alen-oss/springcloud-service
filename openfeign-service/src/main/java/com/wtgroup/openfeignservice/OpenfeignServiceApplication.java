package com.wtgroup.openfeignservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 声明启用OpenFeign通信
@EnableFeignClients
public class OpenfeignServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignServiceApplication.class, args);
    }
}
