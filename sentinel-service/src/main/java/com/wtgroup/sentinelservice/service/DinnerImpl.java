package com.wtgroup.sentinelservice.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

/**
 * 晚餐接口实现类
 */
@Component
public class DinnerImpl implements Dinner {

    @Override
    @SentinelResource("startDinner")
    public String startDinner() {
        return "吃晚餐了！！";
    }

    @Override
    @SentinelResource("endDinner")
    public String endDinner() {
        return "吃饱了！！";
    }
}
