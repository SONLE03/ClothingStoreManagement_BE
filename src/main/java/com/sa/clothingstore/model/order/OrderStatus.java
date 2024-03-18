package com.sa.clothingstore.model.order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("PENDING"),
    CANCELED("CANCELED"),
    DELIVERED("DELIVERED"),
    COMPLETED("COMPLETED");

    private final String orderStatus;
}
