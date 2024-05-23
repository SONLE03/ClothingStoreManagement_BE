package com.sa.clothingstore.dto.request.product;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductItemRequest {
    @NotNull(message = "Product item not null")
    private UUID productItemId;
    @NotNull(message = "Size item not null")
    private int size;
    @NotNull(message = "Color item not null")
    private int color;
}
