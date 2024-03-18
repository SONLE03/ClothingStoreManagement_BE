package com.sa.clothingstore.model.user;

import com.sa.clothingstore.model.CommonModel;

import com.sa.clothingstore.model.attribute.Image;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class BaseUser extends CommonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    @Column(name = "role")
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @Column(name = "isActive", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive;
    public BaseUser(@NotNull BaseUser user){
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dateOfBirth = user.dateOfBirth;
        this.image = user.getImage();
        this.role = user.getRole();
    }
}
