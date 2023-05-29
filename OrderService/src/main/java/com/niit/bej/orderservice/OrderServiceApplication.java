package com.niit.bej.orderservice;

import com.niit.bej.orderservice.filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JWTFilter> registerFilterBean() {
        FilterRegistrationBean<JWTFilter> jwtFilterBean = new FilterRegistrationBean<>();
        jwtFilterBean.setFilter(new JWTFilter());
        jwtFilterBean.addUrlPatterns("/userOrder/user/*");
        return jwtFilterBean;
    }

}
