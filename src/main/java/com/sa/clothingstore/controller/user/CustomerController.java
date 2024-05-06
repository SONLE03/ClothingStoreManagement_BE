package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.response.order.OrderResponse;
import com.sa.clothingstore.service.user.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(APIConstant.CUSTOMERS)
@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(APIConstant.CREATE_ADDRESS)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomerAddress(@PathVariable UUID userId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.createAddress(userId, addressRequest);
        return "Customer address created successfully";
    }
    @PutMapping(APIConstant.UPDATE_ADDRESS)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomerAddress(@PathVariable UUID addressId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.updateAddress(addressId ,addressRequest);
        return "Customer address modified successfully";
    }

}
