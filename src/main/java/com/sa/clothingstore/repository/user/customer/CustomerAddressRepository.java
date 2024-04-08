package com.sa.clothingstore.repository.user.customer;

import com.sa.clothingstore.model.user.customer.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {
}