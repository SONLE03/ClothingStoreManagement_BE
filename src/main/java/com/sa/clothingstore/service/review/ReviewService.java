package com.sa.clothingstore.service.review;

import com.sa.clothingstore.dto.request.review.ReviewRequest;
import com.sa.clothingstore.dto.response.review.ReviewResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<ReviewResponse> getReviewsByProduct(UUID productId);
    void createReview(List<MultipartFile> images, ReviewRequest reviewRequest);
    void updateReview(UUID reviewId, List<MultipartFile> images, ReviewRequest reviewRequest) throws IOException;
    void deleteReview(UUID reviewId) throws IOException;
}
