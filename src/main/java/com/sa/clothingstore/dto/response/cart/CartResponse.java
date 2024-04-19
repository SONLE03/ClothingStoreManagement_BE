package com.sa.clothingstore.dto.response.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CartResponse {
    @JsonProperty("product_item")
    private UUID productItemId;
    @JsonProperty("quantity")
    private Integer quantity;
}
