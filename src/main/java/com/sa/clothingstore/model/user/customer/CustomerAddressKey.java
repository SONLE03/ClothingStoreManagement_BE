package com.sa.clothingstore.model.user.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CustomerAddressKey implements Serializable {
    @Column(name = "customer_id")
    private UUID customerId;
    @Column(name = "address_id")
    private UUID addressId;
}
