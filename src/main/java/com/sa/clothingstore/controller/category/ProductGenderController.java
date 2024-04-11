package com.sa.clothingstore.controller.category;

import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.request.category.ProductGenderRequest;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.model.category.ProductGender;
import com.sa.clothingstore.service.category.branch.BranchService;
import com.sa.clothingstore.service.category.productGender.ProductGenderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/productGender")
public class ProductGenderController {
    private final ProductGenderService productGenderService;
    @GetMapping
    public List<ProductGender> getAll() {
        return productGenderService.getAllProductGender();
    }
    @PostMapping
    public void createProductGender(@RequestBody @Valid ProductGenderRequest productGenderRequest, HttpServletResponse response) throws IOException {
        productGenderService.createProductGender(productGenderRequest);
        response.setStatus(201);
        response.getWriter().write("Product Gender was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyProductGender(@PathVariable UUID id, @RequestBody @Valid ProductGenderRequest productGenderRequest, HttpServletResponse response) throws IOException{
        productGenderService.modifyProductGender(id, productGenderRequest);
        response.getWriter().write("Product Gender was modified successfully");
        response.flushBuffer();
    }
    @DeleteMapping(path = "{id}")
    public void deleteProductGender(@PathVariable UUID id, HttpServletResponse response) throws IOException{
        productGenderService.deleteProductGender(id);
        response.getWriter().write("Product Gender was delete successfully");
        response.flushBuffer();
    }
}
