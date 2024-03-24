package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Size;
import com.sa.clothingstore.service.attribute.size.SizeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/size")
public class SizeController {
    private final SizeService sizeService;
    @GetMapping
    public List<Size> getAll() {
        return sizeService.getAllSize();
    }
    @PostMapping
    public void createSize(@RequestBody @Valid SizeRequest SizeRequest, HttpServletResponse response) throws IOException {
        sizeService.createSize(SizeRequest);
        response.setStatus(201);
        response.getWriter().write("Size was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifySize(@PathVariable Integer id, @RequestBody @Valid SizeRequest SizeRequest, HttpServletResponse response) throws IOException{
        sizeService.modifySize(id, SizeRequest);
        response.getWriter().write("Size was modified successfully");
        response.flushBuffer();
    }
    @DeleteMapping(path = "{id}")
    public void deleteSize(@PathVariable Integer id, HttpServletResponse response) throws IOException{
        sizeService.deleteSize(id);
        response.getWriter().write("Size was deleted successfully");
        response.flushBuffer();
    }
}
