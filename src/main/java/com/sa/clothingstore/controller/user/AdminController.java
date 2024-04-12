package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.dto.response.user.UserResponse;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.user.impl.AdminServiceImp;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/user/admin")
@RestController
@AllArgsConstructor
public class AdminController {
    private final AdminServiceImp adminService;
    @GetMapping()
    public List<User> getAllAdmin(){
        return adminService.getAll();
    }
    @PostMapping()
    public void createAdmin(@RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException{
        adminService.createUser(userRequest, Role.ADMIN);
        response.setStatus(201);
        response.getWriter().write("Admin was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyAdmin(@PathVariable UUID id, @RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException{
        adminService.updateUser(id, userRequest);
        response.setStatus(201);
        response.getWriter().write("Admin was created successfully");
        response.flushBuffer();
    }
}
