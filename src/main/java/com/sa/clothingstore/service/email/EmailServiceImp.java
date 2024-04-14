package com.sa.clothingstore.service.email;

import com.sa.clothingstore.dto.request.email.EmailRequest;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.ForgotPasswordRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;

    @Override
    public void sendSimpleMailMessage(EmailRequest request) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(request.to());
        simpleMailMessage.setSubject(request.subject());
        simpleMailMessage.setText(request.text());
        javaMailSender.send(simpleMailMessage);
    }
    @Transactional
    @Override
    public String verifyEmail(String email){
        User user =  userRepository.findByEmail(email)
                                    .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
        int otp = generateOtp();
        EmailRequest emailRequest = EmailRequest.builder()
                                    .to(email)
                                    .text("This is the OTP for your Forgot Password request: " + otp)
                                    .subject("OTP for Forgot Password request")
                                    .build();
        ForgotPassword fp = ForgotPassword.builder()
                            .otp(otp)
                            .expiryDate(new Date(System.currentTimeMillis() + 70 * 1000))
                            .user(user)
                            .build();
        sendSimpleMailMessage(emailRequest);
        forgotPasswordRepository.save(fp);
        return "Email sent for verification!";
    }
    @Override
    public Integer generateOtp(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
