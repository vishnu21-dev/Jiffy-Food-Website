package com.niit.bej.springemail.domain;

import java.util.Objects;

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

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(toEmail, email.toEmail) && Objects.equals(body, email.body) && Objects.equals(subject, email.subject) && Objects.equals(attachment, email.attachment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toEmail, body, subject, attachment);
    }
}
