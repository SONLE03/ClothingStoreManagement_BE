package com.sa.clothingstore.controller.order;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.order.OrderRequest;
import com.sa.clothingstore.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(APIConstant.ORDERS)
@RestController
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody @Valid OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return "Order was created successfully";
    }
    @PutMapping(APIConstant.ORDER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateOrderStatus(@PathVariable UUID orderId, @RequestBody OrderRequest orderRequest){
        return orderService.updateOrderStatus(orderId, orderRequest);
    }
//    @PutMapping(APIConstant.ORDER_ID)
//    @ResponseStatus(HttpStatus.OK)
//    public String updateOrderStatusToDelivered(@PathVariable UUID orderId){
//        orderService.updateOrderStatusToDelivered(orderId);
//        return "Order was delivered successfully";
//    }
//    @PutMapping(APIConstant.ORDER_ID)
//    @ResponseStatus(HttpStatus.OK)
//    public String updateOrderStatusToCompleted(@PathVariable UUID orderId){
//        orderService.updateOrderStatusToCompleted(orderId);
//        return "Order was completed successfully";
//    }

}
