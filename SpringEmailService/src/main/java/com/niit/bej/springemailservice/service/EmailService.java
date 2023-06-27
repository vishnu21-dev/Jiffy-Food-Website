package com.niit.bej.springemailservice.service;

import com.niit.bej.springemailservice.domain.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
}
