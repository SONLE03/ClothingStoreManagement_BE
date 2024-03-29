package com.sa.clothingstore.model.user.admin;

import com.sa.clothingstore.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "admin")
public class Admin extends User {
    public Admin(User user){
        super(user);
    }
}
