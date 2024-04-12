package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.dto.request.email.EmailRequest;
import com.sa.clothingstore.dto.request.user.ChangePasswordRequest;
import com.sa.clothingstore.exception.OtpException;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.ForgotPasswordRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;


@RestController
@RequestMapping("/api/user/forgotPassword")
@AllArgsConstructor
public class ForgotPasswordController {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/verifyEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public String verifyEmail(@PathVariable String email){
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
        emailService.sendSimpleMailMessage(emailRequest);
        forgotPasswordRepository.save(fp);
        return "Email sent for verification!";
    }

    @PostMapping("verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email){
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new OtpException("Invalid OTP for email: " + email));
        if(fp.getExpiryDate().before(Date.from(Instant.now()))){
            forgotPasswordRepository.delete(fp);
            return new ResponseEntity<>("OTP has expiried!", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTP verified!");
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePasswordRequest changePasswordRequest, @PathVariable String email){
        if(!Objects.equals(changePasswordRequest.password(), changePasswordRequest.repeatPassword())){
            return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
        }
        userRepository.updatePassword(email, passwordEncoder.encode(changePasswordRequest.password()));
        return ResponseEntity.ok("Password has been changed!");
    }
    private Integer generateOtp(){
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
