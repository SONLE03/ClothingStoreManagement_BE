package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.dto.request.payment.PaymentRequest;
import com.sa.clothingstore.dto.response.payment.PaymentResponse;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    List<PaymentResponse> getAllPaymentMethod();
    PaymentResponse getPaymentMethodById();
    void createPaymentMethod(PaymentRequest paymentRequest);
    void updatePaymentMethod(Integer paymentId, PaymentRequest paymentRequest);
    void deletePaymentMethod(Integer paymentId);
}
