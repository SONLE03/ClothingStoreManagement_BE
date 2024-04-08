package com.sa.clothingstore.model.user.admin;

import com.sa.clothingstore.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "admin")
public class Admin extends User{
    public Admin(User user){
        super(user);
    }
}
