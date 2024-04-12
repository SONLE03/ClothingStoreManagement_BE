package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.response.user.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.UUID;

public interface UserDetailService {
    UserDetails userDetails();

    UUID getIdLogin();

    String getUsernameLogin();

    Integer getRoleLogin();
    UserResponse getProfile(UUID userId);
    void changePassword(String oldPassword, String newPassword, Principal connectedUser);
}
