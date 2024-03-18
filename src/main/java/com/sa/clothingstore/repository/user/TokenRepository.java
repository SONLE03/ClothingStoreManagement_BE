package com.sa.clothingstore.repository.user;

import com.sa.clothingstore.model.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
}
