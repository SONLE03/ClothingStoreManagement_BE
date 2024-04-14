package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.response.user.AddressResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.user.customer.Address;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.repository.user.customer.AddressRepository;
import com.sa.clothingstore.repository.user.customer.CustomerRepository;
import com.sa.clothingstore.service.user.factory.CustomerServiceFactory;
import com.sa.clothingstore.service.user.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerServiceFactory customerServiceFactory;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<User> getAllUsersByRole(Integer role){
        return customerServiceFactory.getAllUsers(role);
    }
    @Override
    public void createUser(UserRequest userRequest, Role role) {
        userRepository.save(customerServiceFactory.create(userRequest, role));
    }

    @Override
    public void updateUser(UUID userId, UserRequest userRequest) {
        userRepository.save(customerServiceFactory.update(userId, userRequest));
    }


    @Override
    @Transactional
    public void createAddress(UUID userId, AddressRequest addressRequest) {
        if(!customerRepository.existsById(userId)){
            throw new ObjectNotFoundException("Customer not found");
        }
        Address address = Address.builder()
                .postalCode(addressRequest.getPostalCode())
                .ward(addressRequest.getWard())
                .specificAddress(addressRequest.getSpecificAddress())
                .district(addressRequest.getDistrict())
                .province(addressRequest.getProvince())
                .phone(addressRequest.getPhone())
                .isDefault(addressRequest.isDefault())
                .id(userId)
                .build();
        addressRepository.save(address);
    }
    @Override
    @Transactional
    public void updateAddress(UUID addressId, AddressRequest addressRequest){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer address not found"));
        address.setPostalCode(addressRequest.getPostalCode());
        address.setWard(addressRequest.getWard());
        address.setSpecificAddress(addressRequest.getSpecificAddress());
        address.setDistrict(addressRequest.getDistrict());
        address.setProvince(addressRequest.getProvince());
        address.setPhone(addressRequest.getPhone());
        address.setDefault(addressRequest.isDefault());
        addressRepository.save(address);
    }
}
