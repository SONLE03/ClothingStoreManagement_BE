package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.ProductGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGenderRepository extends JpaRepository<ProductGender, Integer> {

}
