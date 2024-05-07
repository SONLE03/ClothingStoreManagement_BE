package com.sa.clothingstore.repository.review;

import com.sa.clothingstore.dto.response.review.ReviewResponse;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("SELECT NEW com.sa.clothingstore.dto.response.review.ReviewResponse(r.id, r.updatedAt, r.customer.fullName, i.url, r.content) " +
            "FROM Review r " +
            "JOIN r.images i " +
            "WHERE r.product = ?1 " +
            "ORDER BY r.updatedAt DESC")
    List<ReviewResponse> getReviewByProduct(Product product);
}
