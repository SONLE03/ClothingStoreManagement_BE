package com.sa.clothingstore.model.user.staff;

import com.sa.clothingstore.model.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "permissionName")
    private String permissionName;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "staff_permission",
            joinColumns = @JoinColumn(name = "staff_role"),
            inverseJoinColumns = @JoinColumn(name = "permission")
    )
    private List<StaffRole> staffRoles;
}
