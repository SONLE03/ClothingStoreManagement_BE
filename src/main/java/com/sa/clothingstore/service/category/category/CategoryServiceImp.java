package com.sa.clothingstore.service.category.category;

import com.sa.clothingstore.dto.request.category.CategoryRequest;
import com.sa.clothingstore.dto.response.category.CategoryResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.category.Category;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.repository.category.CategoryRepository;
import com.sa.clothingstore.repository.category.ProductGenderRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductGenderRepository productGenderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(!productGenderRepository.existsById(categoryRequest.getProductGender())){
            new ObjectNotFoundException("Product gender not found");
        }
        var productGender = productGenderRepository.getById(categoryRequest.getProductGender());
        Category category = Category.builder()
                .name(categoryRequest.getName())
                .productGender(productGender)
                .build();
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponse.class);
    }

    @Override
    public Category modifyCategory(UUID categoryId, CategoryRequest categoryRequest) {
        if(!categoryRepository.existsById(categoryId)){
            new ObjectNotFoundException("Category not found");
        }
        if(!productGenderRepository.existsById(categoryRequest.getProductGender())){
            new ObjectNotFoundException("Product gender not found");
        }
        var productGender = productGenderRepository.getById(categoryRequest.getProductGender());
        Category category = categoryRepository.getById(categoryId);
        category.setName(categoryRequest.getName());
        category.setProductGender(productGender);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            new ObjectNotFoundException("Category not found");
        }
        categoryRepository.deleteById(categoryId);
    }
}
