package com.niit.bej.springemail.service;

import com.niit.bej.springemail.domain.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class EmailSenderService implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleEmail(Email email, String emailDetails) {


        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("jiffyfoodApp@gmail.com");
            mimeMessageHelper.setTo(email.getToEmail());
            mimeMessageHelper.setText(email.getBody());
            mimeMessageHelper.setSubject(email.getSubject());

            FileSystemResource fileSystemResource = new FileSystemResource(new File(email.getAttachment()));

            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            mailSender.send(mimeMessage);
            System.out.println("Mail sent...");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(sender);
//        mailMessage.setTo(emailDetails);
//        mailMessage.setText(email.getBody());
//        mailMessage.setSubject(email.getSubject());
//
//        mailSender.send(mailMessage);
//        return "Mail sent successfully...";
        return "Error";
    }
}