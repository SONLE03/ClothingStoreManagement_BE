package com.sa.clothingstore.dto.request.authentication;

import com.sa.clothingstore.constant.validation.PhoneNumberFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotEmpty(message = "Missing email")
    @Email(message = "Invalid email")
    private String email;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    @NotEmpty(message = "Missing user full name")
    private String fullname;
    @NotEmpty(message = "Missing password")
    @Min(value = 8, message = "Password must be at least 8 characters")
    private String password;
}
