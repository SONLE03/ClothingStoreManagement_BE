package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsersByRole(Integer role);
    void createUser(UserRequest userRequest, Role role);
    void updateUser(UUID userId, UserRequest userRequest);
}
