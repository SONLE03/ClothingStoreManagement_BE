package com.sa.clothingstore.repository.attribute;

import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Modifying
    @Query("DELETE FROM Image i WHERE i.product = :product")
    void deleteByProduct(Product product);
    @Query("SELECT i.id FROM Image i WHERE i.product = :product")
    List<UUID> getImageByProduct(Product product);
}
