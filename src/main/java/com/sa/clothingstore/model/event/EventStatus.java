package com.sa.clothingstore.model.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {
    ACTIVE("ACTIVE"),
    EXPIRED("EXPIRED"),
    DISABLED("DISABLED");
    private final String eventStatus;
}
