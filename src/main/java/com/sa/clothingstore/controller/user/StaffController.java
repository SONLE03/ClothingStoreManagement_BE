package com.sa.clothingstore.controller.user;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.service.user.impl.AdminServiceImp;
import com.sa.clothingstore.service.user.service.StaffService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/staff")
@RestController
@AllArgsConstructor
public class StaffController {
    private final StaffService staffService;
    @GetMapping()
    public List<User> getAllStaff(){
        return staffService.getAll();
    }
    @PostMapping()
    public void createStaff(@RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException {
        staffService.createUser(userRequest);
        response.setStatus(201);
        response.getWriter().write("Staff was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyStaff(@PathVariable UUID id, @RequestBody @Valid UserRequest userRequest, HttpServletResponse response) throws IOException{
        staffService.updateUser(id, userRequest);
        response.setStatus(201);
        response.getWriter().write("Staff was created successfully");
        response.flushBuffer();
    }
}
