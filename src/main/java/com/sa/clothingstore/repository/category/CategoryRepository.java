package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
