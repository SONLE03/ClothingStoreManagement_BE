package com.sa.clothingstore.service.product;

import com.sa.clothingstore.dto.request.product.ProductItemRequest;
import com.sa.clothingstore.dto.request.product.ProductRequest;
import com.sa.clothingstore.dto.response.product.ProductResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.product.Product;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.product.ProductStatus;
import com.sa.clothingstore.repository.attribute.ColorRepository;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.attribute.SizeRepository;
import com.sa.clothingstore.repository.category.BranchRepository;
import com.sa.clothingstore.repository.category.CategoryRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.repository.product.ProductRepository;
import com.sa.clothingstore.service.user.impl.UserDetailServiceImp;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.util.FileUpload;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.RepositoryType;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ProductItemRepository productItemRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final UserDetailService userDetailService;

    private final ModelMapper modelMapper;
    private final FileUpload fileUpload;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    @Override
    public List<ProductItem> getDetailProduct(UUID productId) {
        return productItemRepository.getProductItemByProduct(productId);
    }
    @Override
    @Transactional
    public void createProduct(ProductRequest productRequest) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        if(!categoryRepository.existsById(productRequest.getCategory()))
            new ObjectNotFoundException("Category not found");
        if(!branchRepository.existsById(productRequest.getBranch()))
            new ObjectNotFoundException("Branch not found");

        // Flow: Product -> Product_Image -> Product_Item
        Product product = Product.builder()
                .product_Name(productRequest.getProduct_Name())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .category(categoryRepository.getById(productRequest.getCategory()))
                .branch(branchRepository.getById(productRequest.getBranch()))
        // Change product status
                .productStatus(ProductStatus.ACTIVE)
                .build();
        productRepository.save(product);
        productRequest.getImageRequests().forEach(image -> {
            if(!image.getUrl().isEmpty()) {
                imageRepository.save(
                        Image.builder()
                                .url(image.getUrl())
                                .product(product)
                                .build()
                );
            }
        });
        productRequest.getProductItemRequests().forEach(item -> {
            if(!sizeRepository.existsById(item.getSize()) || !colorRepository.existsById(item.getColor())){
                new ObjectNotFoundException("Color or Size not found");
            }
            productItemRepository.save(
                    ProductItem.builder()
                            .product(product)
                            .color(colorRepository.getById(item.getColor()))
                            .size(sizeRepository.getById(item.getSize()))
                            .quantity(0)
                            .build()
            );
        });
        product.setCommonCreate(userDetailService.getIdLogin());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "ms");
    }

    @Override
    @Transactional
    public void updateProduct(UUID productId, ProductRequest productRequest) {
        if(!productRepository.existsById(productId))
            new ObjectNotFoundException("Product not found");
        if(!categoryRepository.existsById(productRequest.getCategory()))
            new ObjectNotFoundException("Category not found");
        if(!branchRepository.existsById(productRequest.getBranch()))
            new ObjectNotFoundException("Branch not found");
        Product product = productRepository.getById(productId);
        product.setProduct_Name(productRequest.getProduct_Name());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setCategory(categoryRepository.getById(productRequest.getCategory()));
        product.setBranch(branchRepository.getById(productRequest.getBranch()));
        productRepository.save(product);

        imageRepository.deleteByProduct(product);
        productRequest.getImageRequests().forEach(image -> {
            if(!image.getUrl().isEmpty()) {
                imageRepository.save(
                        Image.builder()
                                .url(image.getUrl())
                                .product(product)
                                .build()
                );
            }
        });
        productRequest.getProductItemRequests().forEach(item -> {
            if(productItemRepository.getProductItemByProductAndAttribute(product, item.getSize(), item.getColor()) == null){
                productItemRepository.save(
                        ProductItem.builder()
                                .product(product)
                                .color(colorRepository.getById(item.getColor()))
                                .size(sizeRepository.getById(item.getSize()))
                                .build()
                );
            }
        });
        product.setCommonUpdate(userDetailService.getIdLogin());
    }

    @Override
    public void deleteProduct(UUID productId) {
        if(!productRepository.existsById(productId)){
            new ObjectNotFoundException("Product not found");
        }

    }
}
