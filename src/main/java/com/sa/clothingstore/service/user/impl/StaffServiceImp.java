package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.factory.StaffServiceFactory;
import com.sa.clothingstore.service.user.service.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StaffServiceImp implements StaffService {

    private final StaffServiceFactory staffServiceFactory;
    private final UserRepository userRepository;
    @Override
    public List<User> getAll(){
        Optional<List<User>> usersOptional = userRepository.findByRole(1);
        List<User> users = usersOptional.orElseThrow(() -> new ObjectNotFoundException("No users found with role staff"));
        return users;
    }
    @Override
    public void createUser(UserRequest userRequest) {
        userRepository.save(staffServiceFactory.create(userRequest));
    }

    @Override
    public void updateUser(UUID userId, UserRequest userRequest) {
        userRepository.save(staffServiceFactory.update(userId, userRequest));
    }

}
