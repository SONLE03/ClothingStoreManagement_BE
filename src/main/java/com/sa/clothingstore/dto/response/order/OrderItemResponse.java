package com.sa.clothingstore.dto.response.order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemResponse {
    private UUID productItem;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;
}
