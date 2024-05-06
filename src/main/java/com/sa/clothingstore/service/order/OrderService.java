package com.sa.clothingstore.service.order;

import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.dto.response.order.OrderItemResponse;
import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.dto.response.report.MonthlyRevenueResponse;
import com.sa.clothingstore.model.order.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    List<OrderResponse> getAllOrder();
    List<OrderResponse> getOrderByStatus(Integer status);
    List<OrderResponse> getAllOrderByCustomer(UUID customerId);
    List<OrderItemResponse> getOrderDetail(UUID orderId);
    void createOrder(OrderRequest orderRequest);
    String updateOrderStatus(UUID orderId, OrderRequest orderRequest);
}

