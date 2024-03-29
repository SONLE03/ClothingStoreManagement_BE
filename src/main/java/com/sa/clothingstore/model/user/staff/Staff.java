package com.sa.clothingstore.model.user.staff;

import com.sa.clothingstore.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "staff")
public class Staff extends User {
    @ManyToOne
    @JoinColumn(name = "staff_role")
    private StaffRole staffRole;
    public Staff(User user, StaffRole staffRole){
        super(user);
        this.staffRole = staffRole;
    }

}
