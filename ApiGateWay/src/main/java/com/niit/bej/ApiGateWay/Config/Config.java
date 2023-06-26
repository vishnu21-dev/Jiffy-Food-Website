package com.niit.bej.ApiGateWay.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RouteLocator myRoute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/userAuth/**").uri("http://localhost:8084/"))//authentication
                .route(p -> p.path("/merchantZomato/**").uri("http://localhost:8082/"))//merchantService
                .route(p -> p.path("/userOrder/**").uri("http://localhost:8083/"))//Order Service
                .route(p -> p.path("/mail/**").uri("http://localhost:8085/"))
                .build();
    }
}
