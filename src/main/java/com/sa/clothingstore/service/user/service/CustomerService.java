package com.sa.clothingstore.service.user.service;

import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.response.user.AddressResponse;

import java.util.UUID;


public interface CustomerService extends UserService {
    void createAddress(UUID userId, AddressRequest addressRequest);
    void updateAddress(UUID addressId, AddressRequest addressRequest);
}
