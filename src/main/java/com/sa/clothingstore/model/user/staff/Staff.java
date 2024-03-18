package com.sa.clothingstore.model.user.staff;

import com.sa.clothingstore.model.user.BaseUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "staff")
public class Staff extends BaseUser {
    @ManyToOne
    @JoinColumn(name = "staff_role")
    private StaffRole staffRole;
    public Staff(BaseUser baseUser, StaffRole staffRole){
        super(baseUser);
        this.staffRole = staffRole;
    }

}
