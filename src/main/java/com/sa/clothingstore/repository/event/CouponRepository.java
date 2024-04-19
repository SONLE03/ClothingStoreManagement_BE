package com.sa.clothingstore.repository.event;

import com.sa.clothingstore.model.event.Coupon;
import com.sa.clothingstore.model.event.EventStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    @Modifying
    @Transactional
    @Query("UPDATE Coupon c SET c.eventStatus = 'EXPIRED' WHERE c.endDate < :currentDate AND c.eventStatus = 'ACTIVE'")
    void updateExpiredStatus(Date currentDate);
    @Query("SELECT c FROM Coupon c ORDER BY CASE WHEN c.endDate >= CURRENT_DATE AND c.eventStatus = 'ACTIVE' THEN 0 ELSE 1 END, c.endDate ASC")
    List<Coupon> findAllSortedByEndDateAndStatus();
    List<Coupon> findByEventStatus(EventStatus eventStatus);
}
