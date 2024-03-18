package com.sa.clothingstore.repository.cart;

import com.sa.clothingstore.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
