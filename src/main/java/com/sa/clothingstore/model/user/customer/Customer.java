package com.sa.clothingstore.model.user.customer;

import com.sa.clothingstore.model.user.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    Set<CustomerAddress> customerAddressSet;
    public Customer(User user){
        super(user);
    }
    public Customer(User user, Address address) {
        super(user);
        this.customerAddressSet = new HashSet<>();
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomer(this);
        customerAddress.setAddress(address);
        this.customerAddressSet.add(customerAddress);
    }
}
