package com.sa.clothingstore.repository.address;

import com.sa.clothingstore.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
