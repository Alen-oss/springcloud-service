package com.wtgroup.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


// 全局过滤器实例，需实现GlobalFilter接口
// https://docs.spring.io/spring-cloud-gateway/docs/2.2.7.BUILD-SNAPSHOT/reference/html/
@Slf4j
@Component
public class ElapsedFilter implements GlobalFilter, Ordered {

    private static String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(final ServerWebExchange exchange, GatewayFilterChain chain) {

        // 前置处理部分
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());

        // chain.filter.then()是后置处理部分
        return chain.filter(exchange).then(
                Mono.<Void>fromRunnable(new Runnable() {
                    public void run() {
                        Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
                        if (startTime != null) {
                            log.info(exchange.getRequest().getRemoteAddress() + "|" + exchange.getRequest().getPath() + "| COST: " + (System.currentTimeMillis() - startTime) + "ms");
                        }
                    }
                })
        );
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
