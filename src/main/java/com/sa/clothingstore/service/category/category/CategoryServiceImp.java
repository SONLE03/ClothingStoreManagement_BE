package com.sa.clothingstore.service.category.category;

import com.sa.clothingstore.dto.request.category.CategoryRequest;
import com.sa.clothingstore.dto.response.category.CategoryResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.repository.category.CategoryRepository;
import com.sa.clothingstore.repository.category.ProductGenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductGenderRepository productGenderRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(!productGenderRepository.existsById(categoryRequest.getProductGender())){
            new ObjectNotFoundException("Product gender not found");
        }
        Optional<ProductGender> productGender = productGenderRepository.findById(categoryRequest.getProductGender());

        return null;
    }

    @Override
    public Category modifyCategory(UUID id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategory(UUID id) {

    }
}
