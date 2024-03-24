package com.sa.clothingstore.model.user.admin;

import com.sa.clothingstore.model.user.BaseUser;
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
public class Admin extends BaseUser {
    public Admin(BaseUser baseUser){
        super(baseUser);
    }
}
