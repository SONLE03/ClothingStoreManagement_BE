package com.sa.clothingstore.repository.category;

import com.sa.clothingstore.model.category.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    Optional<Branch> findByName(String name);
}
