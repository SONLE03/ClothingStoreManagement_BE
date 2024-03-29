package com.sa.clothingstore.service.authentication;

import com.sa.clothingstore.dto.request.authentication.AuthenticationRequest;
import com.sa.clothingstore.dto.request.authentication.RegisterRequest;
import com.sa.clothingstore.dto.response.authentication.AuthenticationResponse;
import com.sa.clothingstore.model.user.User;

public interface AuthenticationService {
    User signup(RegisterRequest registerRequest);
    User authenticate(AuthenticationRequest authenticationRequest);

}
