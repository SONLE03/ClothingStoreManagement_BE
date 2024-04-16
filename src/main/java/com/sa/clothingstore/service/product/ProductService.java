package com.sa.clothingstore.service.product;

import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.model.product.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProduct();
    void createProduct(ProductRequest productRequest);
    void updateProduct(UUID productId, ProductRequest productRequest);
    void deleteProduct(UUID productId);
}
