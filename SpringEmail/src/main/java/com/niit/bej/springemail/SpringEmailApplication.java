package com.niit.bej.springemail;

import com.niit.bej.springemail.service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class SpringEmailApplication {

    @Autowired(required = true)
    private EmailSenderService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringEmailApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {

        service.sendEmailWithAttachment("jiffyfoodApp@gmail.com",
                "Registration Confirmed! Thank you for registering with Jiffy. " +
                        "Check out our website for more details!",
                "Registration request confirmation",
                "D:\\WelcomeMessage.jpg");
    }

}
