package com.sa.clothingstore.dto.request.product;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductItemRequest {

    private UUID productItemId;
    private int size;
    private int color;
}
