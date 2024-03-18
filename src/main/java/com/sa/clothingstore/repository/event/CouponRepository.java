package com.sa.clothingstore.repository.event;

import com.sa.clothingstore.model.event.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
}
