package com.sourcery.oirs.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

        public void sendEmail(String to, String subject, String textBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(textBody);
        try {
            this.javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
