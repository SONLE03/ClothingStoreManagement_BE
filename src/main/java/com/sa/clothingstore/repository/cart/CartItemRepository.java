package com.sa.clothingstore.repository.cart;

import com.sa.clothingstore.model.cart.CartItem;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByCustomer(Customer customer);
    CartItem findByCustomerAndProductItem(Customer customer, ProductItem productItem);
    void deleteByCustomerAndProductItem(Customer customer, ProductItem productItem);
}
