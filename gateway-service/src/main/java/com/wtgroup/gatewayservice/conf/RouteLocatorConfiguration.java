package com.wtgroup.gatewayservice.conf;

import com.wtgroup.gatewayservice.filter.RequestFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteLocatorConfiguration {

    /**
     * 创建Route，适配局部过滤器
     */
    @Bean
    public RouteLocator createRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("customer_route", r -> r.path("/customer/**")
                        .filters(f -> f.filter(new RequestFilter()).stripPrefix(1)).uri("lb://nacos-service"))
                        .build();
    }
}
