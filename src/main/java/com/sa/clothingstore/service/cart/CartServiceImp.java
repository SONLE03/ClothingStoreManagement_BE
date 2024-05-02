package com.sa.clothingstore.service.cart;

import com.sa.clothingstore.dto.request.cart.CartRequest;
import com.sa.clothingstore.dto.response.cart.CartResponse;
import com.sa.clothingstore.dto.response.product.ProductItemResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.cart.CartItem;
import com.sa.clothingstore.model.cart.CartItemKey;
import com.sa.clothingstore.model.product.ProductItem;
import com.sa.clothingstore.model.user.customer.Customer;
import com.sa.clothingstore.repository.cart.CartItemRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.repository.user.customer.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CartServiceImp implements CartService{
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductItemRepository productItemRepository;

    @Override
    @Transactional
    public List<CartResponse> getProductInCart(UUID customerId) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + customerId));
        List<CartResponse> list = cartItemRepository.findCartResponsesByCustomer(customer);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis() + "ms");
        return list;
    }

    @Override
    @Transactional
    public void importProductToCart(UUID customerId, CartRequest cartRequest) {
        // flow: customer -> product
        //                      -> product quantity (true)
        //                          -> product existed in cart
        //                                -> new product in cart (false)
        //                                -> check total quantity (true)
        //                                -> save
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + customerId));

        UUID productItemId = cartRequest.getProductItemId();

        ProductItem productItem = productItemRepository.findById(productItemId)
                .orElseThrow(() ->  new ObjectNotFoundException("ProductItem not found with ID: " + productItemId));

        Integer quantity = cartRequest.getQuantity();
        if(quantity > productItem.getQuantity()) {
            throw new ObjectNotFoundException("The quantity of products available must be greater than the quantity you want to buy");
        }

        CartItem cartItem = cartItemRepository.findByCustomerAndProductItem(customer, productItem);
        if(cartItem != null){
            quantity += cartItem.getQuantity();
            if(quantity > productItem.getQuantity()) {
                throw new ObjectNotFoundException("The quantity of products available must be greater than the quantity you want to buy");
            }
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }else {
            CartItemKey cartItemKey = new CartItemKey();
            cartItemKey.setProductItemId(productItemId);
            cartItemKey.setCustomerId(customerId);

            CartItem item = new CartItem();
            item.setId(cartItemKey);
            item.setProductItem(productItem);
            item.setCustomer(customer);
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
    }

    @Override
    @Transactional
    public void updateProductInCart(UUID customerId, List<CartRequest> cartRequestList) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + customerId));
        for(CartRequest request : cartRequestList){
            ProductItem productItem = productItemRepository.findById(request.getProductItemId())
                    .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with ID: " ));
            Integer quantity = request.getQuantity();
            if(quantity > productItem.getQuantity()){
                throw new ObjectNotFoundException("The quantity of products available must be greater than the quantity you want to buy");
            }
            CartItem cartItem = cartItemRepository.findByCustomerAndProductItem(customer, productItem);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    @Transactional
    public void deleteProductInCart(UUID customerId, List<CartRequest> cartRequestList) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ObjectNotFoundException("Customer not found with ID: " + customerId));
        for(CartRequest request : cartRequestList){
            ProductItem productItem = productItemRepository.findById(request.getProductItemId())
                    .orElseThrow(() -> new ObjectNotFoundException("ProductItem not found with ID: " ));
            cartItemRepository.deleteByCustomerAndProductItem(customer, productItem);
        }
    }
}
