package com.sa.clothingstore.controller.authentication;
import com.sa.clothingstore.dto.request.authentication.AuthenticationRequest;
import com.sa.clothingstore.dto.request.authentication.RegisterRequest;
import com.sa.clothingstore.dto.response.authentication.AuthenticationResponse;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.JwtService;
import com.sa.clothingstore.service.authentication.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = authenticationService.signup(registerRequest);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        User authenticatedUser = authenticationService.authenticate(authenticationRequest);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        String refreshToken = jwtService.generateRefreshToken(authenticatedUser);
        AuthenticationResponse loginResponse = new AuthenticationResponse();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setRefreshToken(refreshToken);

        return ResponseEntity.ok(loginResponse);
    }
}
