package com.sa.clothingstore.dto.request.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BranchRequest {
    @NotEmpty(message = "Thiếu tên nhãn hàng")
    private String name;
}
