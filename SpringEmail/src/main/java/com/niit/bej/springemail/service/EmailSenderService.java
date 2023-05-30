package com.niit.bej.springemail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class EmailSenderService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public String sendEmailWithAttachment(String toEmail, String body, String subject, String attachment) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("jiffyfoodApp@gmail.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(subject);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));

            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            mailSender.send(mimeMessage);
            System.out.println("Mail sent...");
            return toEmail;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
