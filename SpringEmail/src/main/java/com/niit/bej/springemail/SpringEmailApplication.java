package com.niit.bej.springemail;

import com.niit.bej.springemail.configuration.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringEmailApplication {

    @Autowired
    private EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailApplication.class, args);
    }

}
