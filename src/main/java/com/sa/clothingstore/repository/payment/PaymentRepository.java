package com.sa.clothingstore.repository.payment;

import com.sa.clothingstore.model.payment.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentMethod, Integer> {
}
