package com.sa.clothingstore.service.payment;

import com.sa.clothingstore.dto.request.payment.PaymentRequest;
import com.sa.clothingstore.dto.response.payment.PaymentResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.payment.PaymentMethod;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.payment.PaymentRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService{
    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;
    private final ImageRepository imageRepository;
    private final UserDetailService userDetailService;
    @Override
    public List<PaymentResponse> getAllPaymentMethod() {
        return null;
    }

    @Override
    public PaymentResponse getPaymentMethodById() {
        return null;
    }

    @Override
    @Transactional
    public void createPaymentMethod(PaymentRequest paymentRequest) {
        Image image = new Image();
        image.setUrl(paymentRequest.getImage());
        imageRepository.save(image);
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(paymentMethod.getName());
        paymentMethod.setImage(image);
        paymentMethod.setCommonCreate(userDetailService.getIdLogin());
        paymentRepository.save(paymentMethod);
    }

    @Override
    public void updatePaymentMethod(Integer paymentId, PaymentRequest paymentRequest) {
        PaymentMethod paymentMethod = paymentRepository.findById(paymentId).orElseThrow(
                () -> new ObjectNotFoundException("Payment method not found"));
        if(imageRepository.existsById(paymentMethod.getImage().getId())){
            paymentMethod.getImage().setUrl(paymentRequest.getImage());
        }
        paymentMethod.setName(paymentMethod.getName());
        paymentMethod.setCommonUpdate(userDetailService.getIdLogin());
        paymentRepository.save(paymentMethod);
    }

    @Override
    public void deletePaymentMethod(Integer paymentId) {
        if(!paymentRepository.existsById(paymentId)){
            throw new ObjectNotFoundException("Payment method not found");
        }
        paymentRepository.deleteById(paymentId);
    }
}
