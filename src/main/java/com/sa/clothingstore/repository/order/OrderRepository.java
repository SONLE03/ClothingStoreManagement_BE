package com.sa.clothingstore.repository.order;

import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.model.order.Order;
import com.sa.clothingstore.model.order.OrderStatus;
import com.sa.clothingstore.model.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT NEW com.sa.clothingstore.dto.response.order.OrderResponse(o.id, o.createdAt, o.total, o.orderStatus) " +
            "FROM Order o " +
            "WHERE o.customer = ?1 " +
            "ORDER BY o.createdAt DESC")
    List<OrderResponse> getAllOrder();
    @Query("SELECT NEW com.sa.clothingstore.dto.response.order.OrderResponse(o.id, o.createdAt, o.total, o.orderStatus) " +
            "FROM Order o " +
            "WHERE o.orderStatus = ?1 " +
            "ORDER BY o.createdAt DESC")
    List<OrderResponse> getOrderByStatus(OrderStatus orderStatus);
    @Query("SELECT NEW com.sa.clothingstore.dto.response.order.OrderResponse(o.id, o.completedAt, o.total, o.orderStatus) " +
            "FROM Order o " +
            "WHERE o.customer = ?1 " +
            "AND o.orderStatus = 'COMPLETED'" +
            "ORDER BY o.completedAt DESC")
    List<OrderResponse> getOrderByCustomer(Customer customer);
}
