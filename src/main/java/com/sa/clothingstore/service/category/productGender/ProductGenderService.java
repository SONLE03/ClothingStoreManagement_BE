package com.sa.clothingstore.service.category.productGender;

import com.sa.clothingstore.dto.request.category.ProductGenderRequest;
import com.sa.clothingstore.dto.response.category.ProductGenderResponse;
import com.sa.clothingstore.model.category.ProductGender;

import java.util.List;

public interface ProductGenderService {
    List<com.sa.clothingstore.model.category.ProductGender> getAllProductGender();
    ProductGenderResponse createProductGender(ProductGenderRequest productGenderRequest);
    ProductGender modifyProductGender(Integer id, ProductGenderRequest productGenderRequest);
    void deleteProductGender(Integer id);
}
