package com.sa.clothingstore.model.user.customer;

import com.sa.clothingstore.model.user.User;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    Set<CustomerAddress> customerAddressSet;
    public Customer (User user){
        super(user);
    }
}
