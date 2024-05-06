package com.sa.clothingstore.dto.response.order;

import com.sa.clothingstore.model.order.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OrderResponse {
    private UUID orderId;
    private Date orderDate;
    private BigDecimal total;
    private OrderStatus status;

}
