package com.sa.clothingstore.repository.cart;

import com.sa.clothingstore.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}
