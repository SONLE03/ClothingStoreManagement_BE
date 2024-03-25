package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.service.attribute.image.ImageService;
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
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;
    @GetMapping
    public List<Image> getAll() {
        return imageService.getAllImage();
    }
    @PostMapping
    public void createImage(@RequestBody @Valid ImageRequest imageRequest, HttpServletResponse response) throws IOException {
        imageService.createImage(imageRequest);
        response.setStatus(201);
        response.getWriter().write("Image was created successfully");
        response.flushBuffer();
    }
    @PutMapping(path = "{id}")
    public void modifyImage(@PathVariable UUID id, @RequestBody @Valid ImageRequest imageRequest, HttpServletResponse response) throws IOException{
        imageService.modifyImage(id, imageRequest);
        response.getWriter().write("Size was created successfully");
        response.flushBuffer();
    }
    @DeleteMapping(path = "{id}")
    public void deleteImage(@PathVariable UUID id, HttpServletResponse response) throws IOException{
        imageService.deleteImage(id);
        response.getWriter().write("Size was created successfully");
        response.flushBuffer();
    }
}
