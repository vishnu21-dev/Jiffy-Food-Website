package com.niit.bej.springemail.service;

import com.niit.bej.springemail.domain.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
}
