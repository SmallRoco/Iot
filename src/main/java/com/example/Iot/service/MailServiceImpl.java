package com.example.Iot.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class MailServiceImpl {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private long last_time = 0;


    public void sendMessage(String to,String title,String content){

        if(System.currentTimeMillis()-last_time<1800000){
            return;
        }

        last_time = System.currentTimeMillis();

        MimeMessage message = mailSender.createMimeMessage();

        int code = (int)(Math.random()*1000000);

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);

        } catch ( javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);



    }

}
