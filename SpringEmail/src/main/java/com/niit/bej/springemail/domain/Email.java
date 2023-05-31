package com.niit.bej.springemail.domain;

public class Email {

    private String toEmail;
    private String body;
    private String subject;
    private String attachment;

    public Email(String toEmail, String body, String subject, String attachment) {
        this.toEmail = toEmail;
        this.body = body;
        this.subject = subject;
        this.attachment = attachment;
    }

    public Email() {
    }
}
