package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.request.user.ChangePasswordRequest;
import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.exception.OtpException;
import com.sa.clothingstore.exception.PasswordException;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.ForgotPasswordRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
@Service
@AllArgsConstructor
public class UserDetailServiceImp implements UserDetailService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final ForgotPasswordRepository forgotPasswordRepository;
    @Override
    public UserDetails userDetails() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        } else {
            return null;
        }
    }

    @Override
    public UUID getIdLogin() {
        UserDetails userDetails = userDetails();
        if (userDetails == null) {
            return null;
        }
        return ((User) userDetails).getId();
    }

    @Override
    public String getUsernameLogin() {
        User userDetails = (User) userDetails();
        if (userDetails == null) {
            return null;
        }
        return userDetails.getUsername();
    }
    @Override
    public Integer getRoleById(UUID userId){
        return userRepository.getRoleById(userId);
    }
    @Override
    public Integer getRoleLogin() {
        User userDetails = (User) userDetails();
        if (userDetails == null) {
            return null;
        }
        return userDetails.getRole().ordinal();
    }

    @Override
    public User getProfile(UUID userId) {
        if(!userRepository.existsById(userId)){
            new ObjectNotFoundException("User not found");
        }
        return userRepository.getUserDetail(userId);
    }

    @Override
    public String verifyOtp(Integer otp, String email){
        User user =  userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                .orElseThrow(() -> new OtpException("Invalid OTP for email: " + email));
        if(fp.getExpiryDate().before(Date.from(Instant.now()))){
            forgotPasswordRepository.delete(fp);
            new OtpException("OTP has expiried!");
        }
        return "OTP verified!";
    }

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest, String email){
        if(!Objects.equals(changePasswordRequest.password(), changePasswordRequest.repeatPassword())){
            new PasswordException("Please enter the password again!");
        }
        userRepository.updatePassword(email, passwordEncoder.encode(changePasswordRequest.password()));
        return "Password has been changed!";
    }
}
