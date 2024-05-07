package com.sa.clothingstore.controller.review;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.request.review.ReviewRequest;
import com.sa.clothingstore.dto.response.review.ReviewResponse;
import com.sa.clothingstore.service.review.ReviewService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(APIConstant.REVIEWS)
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getReviewByProduct(@PathVariable UUID productId){
        return reviewService.getReviewsByProduct(productId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createReview(@RequestParam("images") @Nullable List<MultipartFile> images, @Valid @ModelAttribute ReviewRequest reviewRequest) throws IOException {
        reviewService.createReview(images, reviewRequest);
        return "Review was created successfully";
    }
    @PutMapping(APIConstant.REVIEW_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateReview(@PathVariable UUID reviewId,@RequestParam("images") @Nullable List<MultipartFile> images, @Valid @ModelAttribute ReviewRequest reviewRequest) throws IOException {
        reviewService.updateReview(reviewId, images, reviewRequest);
        return "Review was modified successfully";
    }
    @DeleteMapping(APIConstant.REVIEW_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteReview(@PathVariable UUID reviewId) throws IOException {
        reviewService.deleteReview(reviewId);
        return "Review was deleted successfully";
    }
}
