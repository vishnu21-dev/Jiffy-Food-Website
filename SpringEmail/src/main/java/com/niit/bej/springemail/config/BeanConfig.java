package com.niit.bej.springemail.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class BeanConfig {

    @Bean
    public FilterRegistrationBean jwtFilterBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/v7/*");
        return filterRegistrationBean;
    }
}
