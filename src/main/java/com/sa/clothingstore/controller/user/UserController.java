package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.authentication.AuthenticationService;
import com.sa.clothingstore.service.user.service.UserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/api/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserDetailService userDetailService;
    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public User authenticatedUser(){
        return authenticationService.me();
    }

    @PostMapping("/changePassword")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public void changePass(@RequestParam String oldPassword, @RequestParam String newPassword, Principal connectedUser){
        userDetailService.changePassword(oldPassword, newPassword, connectedUser);
    }
}
