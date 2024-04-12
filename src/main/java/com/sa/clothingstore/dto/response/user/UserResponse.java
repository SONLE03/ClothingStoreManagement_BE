package com.sa.clothingstore.dto.response.user;

import com.sa.clothingstore.constant.validation.PhoneNumberFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse extends AddressResponse{
    private UUID userId;
    private String email;
    private String phone;
    private String fullname;
    private String password;
    private Date dateOfBirth;
    private String role;
    private String image;
    private boolean enable;
}
