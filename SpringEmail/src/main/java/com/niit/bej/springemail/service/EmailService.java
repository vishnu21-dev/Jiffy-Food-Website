package com.niit.bej.springemail.service;

import com.niit.bej.springemail.domain.Email;
import jakarta.mail.MessagingException;

public interface EmailService {

    String sendEmailWithAttachment(Email email, String emailDetails) throws MessagingException;
}
