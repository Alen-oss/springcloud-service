package com.wtgroup.sentinelservice.controller;

import com.wtgroup.sentinelservice.service.Dinner;
import com.wtgroup.sentinelservice.service.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel")
@Slf4j
public class SentinelController {

    @Autowired
    private Order order;

    @Autowired
    private Dinner dinner;

    @RequestMapping(value = "/do/something", method = RequestMethod.GET)
    public String createOrder() {

        log.info(order.createOrder());
        log.info(order.destroyOrder());
        log.info(dinner.startDinner());
        log.info(dinner.endDinner());
        return "yes, sir !!";
    }
}
