package com.sa.clothingstore.repository.product;

import com.sa.clothingstore.model.product.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductItemRepository extends JpaRepository<ProductItem, UUID> {
}
