package com.niit.bej.springemail.controller;

import com.niit.bej.springemail.domain.Email;
import com.niit.bej.springemail.service.EmailService;
import io.jsonwebtoken.Claims;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody Email email, HttpServletRequest request) {

        System.out.println("header" + request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("email from claims :: " + claims.getSubject());
        String emailDetails = claims.getSubject();
        System.out.println("email :: " + email);

        try {
            return new ResponseEntity<>(emailService.sendEmailWithAttachment(email, emailDetails), HttpStatus.OK);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
