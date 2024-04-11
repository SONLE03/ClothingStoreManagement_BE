package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.ProductGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductGenderRepository extends JpaRepository<ProductGender, UUID> {
    Optional<ProductGender> findByName(String name);
}
