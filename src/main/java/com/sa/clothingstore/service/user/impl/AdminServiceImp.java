package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.factory.AdminServiceFatory;
import com.sa.clothingstore.service.user.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImp implements AdminService {
    private final AdminServiceFatory adminServiceFatory;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsersByRole(Integer role){
        return adminServiceFatory.getAllUsers(role);
    }
    @Override
    public void createUser(UserRequest userRequest, Role role) {
        userRepository.save(adminServiceFatory.create(userRequest, role));
    }

    @Override
    public void updateUser(UUID userId, UserRequest userRequest) {
        userRepository.save(adminServiceFatory.update(userId, userRequest));
    }

}
