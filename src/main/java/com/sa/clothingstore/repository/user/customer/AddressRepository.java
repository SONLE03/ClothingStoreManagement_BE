package com.sa.clothingstore.repository.user.customer;

import com.sa.clothingstore.model.user.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
