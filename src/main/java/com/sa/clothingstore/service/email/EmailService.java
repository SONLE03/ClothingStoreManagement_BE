package com.sa.clothingstore.service.email;

import com.sa.clothingstore.dto.request.email.EmailRequest;

public interface EmailService {
    void sendSimpleMailMessage(EmailRequest request);
}
