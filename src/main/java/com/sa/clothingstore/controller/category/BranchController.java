package com.sa.clothingstore.controller.category;

import com.sa.clothingstore.dto.request.category.BranchRequest;
import com.sa.clothingstore.dto.response.category.BranchResponse;
import com.sa.clothingstore.common.exception.GlobalExceptionHandler;
import com.sa.clothingstore.model.category.Branch;
import com.sa.clothingstore.service.category.branch.BranchService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    @GetMapping
    public List<Branch> getAll() {
        return branchService.getAllBranch();
    }
    @PostMapping
    public void createBranch(@RequestBody BranchRequest branchRequest, HttpServletResponse response) throws IOException {
        branchService.createBranch(branchRequest);
        response.setStatus(201);
        response.getWriter().write("Branch was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyBranch(@PathVariable UUID id, @RequestBody BranchRequest branchRequest, HttpServletResponse response) throws IOException{
        branchService.modifyBranch(id, branchRequest);
        response.getWriter().write("Branch was modified successfully");
        response.flushBuffer();
    }
    @DeleteMapping(path = "{id}")
    public void deleteBranch(@PathVariable UUID id, HttpServletResponse response) throws IOException{
        branchService.deleteBranch(id);
        response.getWriter().write("Branch was delete successfully");
        response.flushBuffer();
    }
}
