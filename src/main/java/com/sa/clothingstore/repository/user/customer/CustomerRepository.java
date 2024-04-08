package com.sa.clothingstore.repository.user.customer;

import com.sa.clothingstore.model.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}