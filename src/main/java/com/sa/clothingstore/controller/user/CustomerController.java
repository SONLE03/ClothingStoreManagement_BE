package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.user.service.CustomerService;
import com.sa.clothingstore.service.user.service.StaffService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/customer")
@RestController
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping()
    public List<User> getAllCustomer(){
        return customerService.getAll();
    }
    @PostMapping()
    public void createCustomer(@RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException {
        customerService.createUser(userRequest);
        response.setStatus(201);
        response.getWriter().write("Customer was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyCustomer(@PathVariable UUID id, @RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException{
        customerService.updateUser(id, userRequest);
        response.setStatus(201);
        response.getWriter().write("Customer was created successfully");
        response.flushBuffer();
    }
    @PostMapping(path = "{id}")
    public void createOtherAddress(@PathVariable UUID id, @RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException{
        customerService.createOtherAddress(id, userRequest);
        response.setStatus(201);
        response.getWriter().write("Address was created successfully");
        response.flushBuffer();
    }
}
