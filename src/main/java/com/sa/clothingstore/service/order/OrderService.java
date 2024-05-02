package com.sa.clothingstore.service.order;

import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.model.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<Order> getAllOrder();
    List<Order> getAllOrderByCustomer(UUID customerId);
    void createOrder(OrderRequest orderRequest);
    String updateOrderStatus(UUID orderId, OrderRequest orderRequest);
}

