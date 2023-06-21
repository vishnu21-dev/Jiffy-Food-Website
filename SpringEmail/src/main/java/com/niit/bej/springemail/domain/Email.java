package com.niit.bej.springemail.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Email {

    private String toEmail;
    private String body;
    private String subject;


}
