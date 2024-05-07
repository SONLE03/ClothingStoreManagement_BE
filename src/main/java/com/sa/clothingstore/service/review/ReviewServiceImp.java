package com.sa.clothingstore.service.review;

import com.sa.clothingstore.constant.APIStatus;
import com.sa.clothingstore.dto.request.review.ReviewRequest;
import com.sa.clothingstore.dto.response.review.ReviewResponse;
import com.sa.clothingstore.exception.BusinessException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.review.Review;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.order.OrderRepository;
import com.sa.clothingstore.repository.product.ProductRepository;
import com.sa.clothingstore.repository.review.ReviewRepository;
import com.sa.clothingstore.repository.user.customer.CustomerRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.util.fileUpload.FileUpload;
import com.sa.clothingstore.util.fileUpload.FileUploadImp;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final FileUploadImp fileUpload;
    private final UserDetailService userDetailService;
    private final OrderRepository orderRepository;

    @Override
    public List<ReviewResponse> getReviewsByProduct(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new BusinessException(APIStatus.PRODUCT_NOT_FOUND));
        return reviewRepository.getReviewByProduct(product);
    }

    @Override
    @Transactional
    public void createReview(List<MultipartFile> images, ReviewRequest reviewRequest) {
        // Validator
        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow(
                () -> new BusinessException(APIStatus.PRODUCT_NOT_FOUND));
        Customer customer = customerRepository.findById(reviewRequest.getCustomerId()).orElseThrow(
                () -> new BusinessException(APIStatus.CUSTOMER_NOT_FOUND));
        if(!orderRepository.hasCustomerPurchasedProduct(customer, product)){
            throw new BusinessException(APIStatus.CUSTOMER_NEVER_PURCHASED_PRODUCT);
        }
        Review review = Review.builder()
                .product(product)
                .customer(customer)
                .content(reviewRequest.getContent())
                .build();
        review.setCommonCreate(userDetailService.getIdLogin());
        reviewRepository.save(review);
        ExecutorService executorService = Executors.newFixedThreadPool(images.size());
        List<Future<Map>> futures = new ArrayList<>();

        for (MultipartFile multipartFile : images) {
            Future<Map> future = executorService.submit(() -> {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
                }
                return fileUpload.upload(multipartFile, "reviews");
            });
            futures.add(future);
        }

        for (int i = 0; i < images.size(); i++) {
            Future<Map> future = futures.get(i);
            try {
                Map result = future.get();
                imageRepository.save(
                        Image.builder()
                                .name((String) result.get("original_filename"))
                                .url((String) result.get("url"))
                                .review(review)
                                .cloudinaryId((String) result.get("public_id"))
                                .build()
                );
            } catch (Exception e) {
                // Xử lý ngoại lệ khi có lỗi xảy ra trong tiến trình tải lên ảnh
            }
        }

        executorService.shutdown();
    }

    @Override
    @Transactional
    public void updateReview(UUID reviewId, List<MultipartFile> images, ReviewRequest reviewRequest) throws IOException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new BusinessException(APIStatus.REVIEW_NOT_FOUND));
        if(review.getCustomer().getId() != reviewRequest.getCustomerId()){
            throw new BusinessException(APIStatus.REVIEW_CUSTOMER_ID_MISMATCH);
        }
        List<Image> imageList = imageRepository.getImageByReview(review);
        for(Image image : imageList){
            fileUpload.delete(image.getCloudinaryId());
        }
        imageRepository.deleteByReview(review);
        ExecutorService executorService = Executors.newFixedThreadPool(images.size());
        List<Future<Map>> futures = new ArrayList<>();

        for (MultipartFile multipartFile : images) {
            Future<Map> future = executorService.submit(() -> {
                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    throw new BusinessException(APIStatus.IMAGE_NOT_FOUND);
                }
                return fileUpload.upload(multipartFile, "reviews");
            });
            futures.add(future);
        }

        for (int i = 0; i < images.size(); i++) {
            Future<Map> future = futures.get(i);
            try {
                Map result = future.get();
                imageRepository.save(
                        Image.builder()
                                .name((String) result.get("original_filename"))
                                .url((String) result.get("url"))
                                .review(review)
                                .cloudinaryId((String) result.get("public_id"))
                                .build()
                );
            } catch (Exception e) {
                // Xử lý ngoại lệ khi có lỗi xảy ra trong tiến trình tải lên ảnh
            }
        }
        review.setContent(reviewRequest.getContent());
        review.setCommonUpdate(userDetailService.getIdLogin());
        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void deleteReview(UUID reviewId) throws IOException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new BusinessException(APIStatus.REVIEW_NOT_FOUND));
        List<Image> imageList = imageRepository.getImageByReview(review);
        for(Image image : imageList){
            fileUpload.delete(image.getCloudinaryId());
        }
        imageRepository.deleteByReview(review);
        reviewRepository.deleteById(reviewId);
    }
}
