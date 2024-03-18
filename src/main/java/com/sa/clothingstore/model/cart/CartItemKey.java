package com.sa.clothingstore.model.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CartItemKey implements Serializable{
    @Column(name = "cart_id")
    private UUID cartId;
    @Column(name = "productItem_id")
    private UUID productItemId;
}
