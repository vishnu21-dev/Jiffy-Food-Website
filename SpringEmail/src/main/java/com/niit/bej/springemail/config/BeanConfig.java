package com.niit.bej.springemail.config;

import com.niit.bej.springemail.filter.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FilterRegistrationBean<JWTFilter> registerFilterBean() {
        FilterRegistrationBean<JWTFilter> jwtFilterBean = new FilterRegistrationBean<>();
        jwtFilterBean.setFilter(new JWTFilter());
        jwtFilterBean.addUrlPatterns("/api/*");
        return jwtFilterBean;
    }
}
