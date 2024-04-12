package com.sa.clothingstore.service.user.factory;


import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.user.admin.Admin;
import com.sa.clothingstore.model.user.staff.Staff;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StaffServiceFactory extends UserServiceFactory{
    public StaffServiceFactory(PasswordEncoder passwordEncoder, ImageRepository imageRepository, UserDetailService userDetailService, UserRepository userRepository) {
        super(passwordEncoder, imageRepository, userDetailService, userRepository);
    }
    @Override
    @Transactional
    protected User createUser(User user, UserRequest userRequest) {
        return new Staff(user);
    }
    @Override
    @Transactional
    protected User updateUser(User user, UserRequest userRequest) {
        return new Staff(user);
    }
}
