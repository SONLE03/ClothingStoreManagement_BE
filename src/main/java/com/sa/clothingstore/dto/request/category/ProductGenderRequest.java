package com.sa.clothingstore.dto.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductGenderRequest {
    @NotEmpty(message = "Thiếu thông tin")
    private String name;
}
