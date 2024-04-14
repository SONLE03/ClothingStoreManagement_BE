package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.user.AddressRequest;
import com.sa.clothingstore.dto.request.user.ChangePasswordRequest;
import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.OtpException;
import com.sa.clothingstore.model.user.ForgotPassword;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.authentication.AuthenticationService;
import com.sa.clothingstore.service.user.factory.UserServiceFactory;
import com.sa.clothingstore.service.user.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping(APIConstant.USERS)
@RestController
@AllArgsConstructor
public class UserController {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final StaffService staffService;
    private final UserDetailService userDetailService;
    private final Map<Integer, UserService> roleToServiceMap;

    @PostConstruct
    private void initRoleToServiceMap() {
        roleToServiceMap.put(0, adminService);
        roleToServiceMap.put(1, staffService);
        roleToServiceMap.put(2, customerService);
    }
    @GetMapping(APIConstant.GETALL)
    public List<User> getAllUsersByRole(@PathVariable Integer role){
        return roleToServiceMap.get(role).getAllUsersByRole(role);
    }

    @GetMapping(APIConstant.USER_ID)
    public User getUserById(@PathVariable UUID userId){
        return userDetailService.getProfile(userId);
    }

    @PostMapping(APIConstant.CHANGEPASSWORD)
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, @PathVariable String email){
       return userDetailService.changePassword(changePasswordRequest, email);
    }
    @PostMapping(APIConstant.VERIFYOTP)
    @ResponseStatus(HttpStatus.OK)
    public String verifyOtp(@PathVariable Integer otp, @PathVariable String email){
        return userDetailService.verifyOtp(otp, email);
    }
    @PostMapping(APIConstant.CREATEUSER)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody @Valid UserRequest userRequest, @PathVariable Integer role){
        roleToServiceMap.get(role).createUser(userRequest, Role.convertIntegerToRole(role));
        return "User created successfully";
    }
    @PutMapping(APIConstant.USER_ID)
    @ResponseStatus(HttpStatus.OK)
    public String updateUser(@PathVariable UUID userId, @RequestBody @Valid UserRequest userRequest){
        roleToServiceMap.get(userDetailService.getRoleById(userId)).updateUser(userId, userRequest);
        return "User modified successfully";
    }

    // Customer service
    @PostMapping(APIConstant.CREATEADDRESS)
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomerAddress(@PathVariable UUID userId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.createAddress(userId, addressRequest);
        return "Customer address created successfully";
    }
    @PutMapping(APIConstant.UPDATEADDRESS)
    @ResponseStatus(HttpStatus.OK)
    public String updateCustomerAddress(@PathVariable UUID addressId, @RequestBody @Valid AddressRequest addressRequest){
        customerService.updateAddress(addressId ,addressRequest);
        return "Customer address modified successfully";
    }
}
