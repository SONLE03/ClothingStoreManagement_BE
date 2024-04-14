package com.sa.clothingstore.service.user.factory;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.user.customer.Address;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.repository.user.customer.AddressRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceFactory extends UserServiceFactory{
    private final AddressRepository addressRepository;
    public CustomerServiceFactory(PasswordEncoder passwordEncoder, ImageRepository imageRepository
            , UserDetailService userDetailService, AddressRepository addressRepository, UserRepository userRepository) {
        super(passwordEncoder, imageRepository, userDetailService, userRepository);
        this.addressRepository = addressRepository;
    }
    @Override
    @Transactional
    protected User createUser(User user, UserRequest userRequest) {
        return new Customer(user);
    }
    @Override
    @Transactional
    protected User updateUser(User user, UserRequest userRequest) {
        return user;
    }
    @Override
    protected List<User> getAllUsersByRole(Integer role) { return getAllUsers(role); }
}
