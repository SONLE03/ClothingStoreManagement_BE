package com.sa.clothingstore.controller.payment;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.service.payment.OnlinePaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/onlPayment")
@RestController
@AllArgsConstructor
public class OnlinePaymentController {
    private final OnlinePaymentService onlinePaymentService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void payment(@PathVariable Integer id, @RequestParam("amount") int orderTotal,
                        @RequestParam("orderInfo") String orderInfo, HttpServletRequest request){
        onlinePaymentService.paymentMethod(id, orderTotal, orderInfo, request);
    }
}
