package com.sa.clothingstore.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ADMIN"),
    STAFF("STAFF"),
    CUSTOMER("CUSTOMER");
    private final String role;
}
