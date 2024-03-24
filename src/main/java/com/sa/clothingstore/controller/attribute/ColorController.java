package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.service.attribute.color.ColorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/color")
public class ColorController {
    private final ColorService colorService;
    @GetMapping
    public List<Color> getAll() {
        return colorService.getAllColor();
    }
    @PostMapping
    public void createColor(@RequestBody @Valid ColorRequest colorRequest, HttpServletResponse response) throws IOException {
        colorService.createColor(colorRequest);
        response.setStatus(201);
        response.getWriter().write("Color was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyColor(@PathVariable Integer id, @RequestBody @Valid ColorRequest colorRequest, HttpServletResponse response) throws IOException{
        colorService.modifyColor(id, colorRequest);
        response.getWriter().write("Color was modified successfully");
        response.flushBuffer();
    }
    @DeleteMapping(path = "{id}")
    public void deleteColor(@PathVariable Integer id, HttpServletResponse response) throws IOException{
        colorService.deleteColor(id);
        response.getWriter().write("Color was deleted successfully");
        response.flushBuffer();
    }
}
