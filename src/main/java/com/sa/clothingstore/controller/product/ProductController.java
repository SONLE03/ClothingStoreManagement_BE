package com.sa.clothingstore.controller.product;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(APIConstant.PRODUCTS)
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping(APIConstant.PRODUCT_ID)
    public List<ProductItem> getDetailProduct(@PathVariable UUID productId){
        return productService.getDetailProduct(productId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody @Valid ProductRequest productRequest){
        productService.createProduct(productRequest);
        return "Product was created successfully";
    }
    @PutMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable UUID productId, @RequestBody @Valid ProductRequest productRequest){
        productService.updateProduct(productId, productRequest);
        return "Product was modified successfully";
    }
    @DeleteMapping(APIConstant.PRODUCT_ID)
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable UUID productId){
        productService.deleteProduct(productId);
        return "Product was deleted successfully";
    }
}
