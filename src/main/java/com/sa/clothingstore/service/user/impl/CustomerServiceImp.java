package com.sa.clothingstore.service.user.impl;

import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.response.user.AddressResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.model.user.customer.CustomerAddress;
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
import java.util.Optional;
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
    public List<User> getAll(){
        Optional<List<User>> usersOptional = userRepository.findByRole(2);
        List<User> users = usersOptional.orElseThrow(() -> new ObjectNotFoundException("No users found with role customer"));
        return users;
    }
    @Override
    public void createUser(UserRequest userRequest) {
        userRepository.save(customerServiceFactory.create(userRequest));
    }

    @Override
    public void updateUser(UUID userId, UserRequest userRequest) {
        userRepository.save(customerServiceFactory.update(userId, userRequest));
    }

    @Override
    @Transactional
    public AddressResponse createOtherAddress(UUID userId, AddressRequest addressRequest) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found"));
        CustomerAddress address = modelMapper.map(addressRequest, CustomerAddress.class);
        customer.getCustomerAddressSet().add(address);
        customerRepository.save(customer);
        return modelMapper.map(address, AddressResponse.class);
    }
}
