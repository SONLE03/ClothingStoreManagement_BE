package com.sa.clothingstore.model.user.customer;

import com.sa.clothingstore.model.user.BaseUser;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
public class Customer extends BaseUser {
    @OneToMany(mappedBy = "customer")
    Set<CustomerAddress> customerAddressSet;
    public Customer (BaseUser user){
        super(user);
    }
}
