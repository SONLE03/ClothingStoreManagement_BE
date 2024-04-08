package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.user.service.UserDetailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UserDetailServiceImp implements UserDetailService {
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
}
