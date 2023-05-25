package com.niit.bej.merchantservice;

import com.niit.bej.merchantservice.filter.JWTFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MerchantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JWTFilter> registerFilterBean() {
        FilterRegistrationBean<JWTFilter> jwtFilterBean = new FilterRegistrationBean<>();
        jwtFilterBean.setFilter(new JWTFilter());
        jwtFilterBean.addUrlPatterns("/merchantZomato/merchant/*");
        return jwtFilterBean;
    }

}
