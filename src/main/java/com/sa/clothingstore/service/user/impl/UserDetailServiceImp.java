package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.exception.PasswordException;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class UserDetailServiceImp implements UserDetailService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
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
    public Integer getRoleLogin() {
        User userDetails = (User) userDetails();
        if (userDetails == null) {
            return null;
        }
        return userDetails.getRole().ordinal();
    }

    @Override
    public UserResponse getProfile(UUID userId) {
        return null;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword, Principal connectedUser){
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        Optional<User> optional = userRepository.findById(user.getId());
        if (optional.isEmpty()) {
            throw new ObjectNotFoundException("User not found");
        }
        if (!passwordEncoder.matches(oldPassword, optional.get().getPassword())) {
            throw new PasswordException("Incorrect old password");
        }
        optional.get().setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(optional.get());
    }
}
