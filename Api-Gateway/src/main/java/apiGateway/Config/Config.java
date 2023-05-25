package apiGateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RouteLocator myroute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p.path("/api/v1/**").uri("http://localhost:8084/"))//authentication
                .route(p -> p.path("/api/v2/**").uri("http://localhost:8082/"))//merchantService
                .route(p -> p.path("/api/v2/**").uri("http://localhost:8083/"))//Order Service
                .build();
    }
}