package com.sa.clothingstore.service.category.productGender;

import com.sa.clothingstore.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.dto.request.category.ProductGenderRequest;
import com.sa.clothingstore.dto.response.category.ProductGenderResponse;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.repository.category.ProductGenderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductGenderImp implements ProductGenderService {
    private final ProductGenderRepository productGenderRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public List<ProductGender> getAllProductGender() {
        return productGenderRepository.findAll();
    }

    @Override
    public ProductGenderResponse createProductGender(ProductGenderRequest productGenderRequest) {
        Optional<ProductGender> productGender = productGenderRepository.findByName(productGenderRequest.getName());
        if(productGender.isPresent()){
            throw new ObjectAlreadyExistsException("Product Gender already exists");
        }
        return modelMapper
                .map(productGenderRepository
                                .save(modelMapper
                                        .map(productGenderRequest, ProductGender.class))
                        , ProductGenderResponse.class);
    }

    @Override
    public ProductGender modifyProductGender(UUID id, ProductGenderRequest productGenderRequest) {
        ProductGender productGender = productGenderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product Gender not found")
                );
        String newName = productGender.getName();
        if (productGender.getName().equals(newName)) {
            throw new ObjectAlreadyExistsException("Product Gender already existed");
        }
        productGender.setName(newName);
        return productGenderRepository.save(productGender);
    }

    @Override
    public void deleteProductGender(UUID id) {
        ProductGender productGender = productGenderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product Gender not found")
                );
        productGenderRepository.delete(productGender);
    }
}
