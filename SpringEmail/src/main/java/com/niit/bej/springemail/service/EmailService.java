package com.niit.bej.springemail.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    String sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) throws MessagingException;
}
