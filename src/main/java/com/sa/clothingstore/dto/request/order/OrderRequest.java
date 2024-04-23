package com.sa.clothingstore.dto.request.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    @NotNull(message = "Missing customer")
    private UUID customerId;
    @NotNull(message = "Missing address")
    private UUID addressId;
    private UUID coupon;
//    @NotNull(message = "Missing payment method")
    private Integer paymentMethod;
    private String note;
        private List<OrderItemRequest> items;
}
