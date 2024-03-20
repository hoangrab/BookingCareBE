package com.n7.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Value("spring.mail.username")
    private String userFrom;
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String email, String code) throws MailException, MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(userFrom);
        messageHelper.setTo("Receiver");
        messageHelper.setSubject("Title");
        code = "Biệt đội chơi đá";
        messageHelper.setText("<html><body><h1>Xác thực tài khoản</h1><a href='" + code + "'>VERIFY</a></body></html>",true);
        mailSender.send(message);
    }
}