package com.sa.clothingstore.service.email;

import com.sa.clothingstore.dto.request.email.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMailMessage(EmailRequest request) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(request.to());
        simpleMailMessage.setSubject(request.subject());
        simpleMailMessage.setText(request.text());
        javaMailSender.send(simpleMailMessage);
    }
}
