package com.sa.clothingstore.dto.request.attribute;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ImageRequest {
    @NotEmpty(message = "Thiếu đường dẫn")
    private String url;
}
