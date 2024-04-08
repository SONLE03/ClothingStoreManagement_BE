package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAll();
    void createUser(UserRequest userRequest);
    void updateUser(UUID userId, UserRequest userRequest);
}
