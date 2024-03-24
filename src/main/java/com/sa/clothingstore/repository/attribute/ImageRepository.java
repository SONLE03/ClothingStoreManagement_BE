package com.sa.clothingstore.repository.attribute;

import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
