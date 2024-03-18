package com.sa.clothingstore.repository.event;

import com.sa.clothingstore.model.event.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
}
