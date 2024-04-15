package com.sa.clothingstore.controller.attribute;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.service.attribute.image.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(APIConstant.IMAGES)
public class ImageController {
    private final ImageService imageService;
    @GetMapping
    public List<Image> getAll() {
        return imageService.getAllImage();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createImage(@RequestBody @Valid ImageRequest imageRequest) {
        imageService.createImage(imageRequest);
        return "Image was created successfully";
    }
    @PutMapping(APIConstant.IMAGE_ID)
    @ResponseStatus(HttpStatus.OK)
    public String modifyImage(@PathVariable UUID id, @RequestBody @Valid ImageRequest imageRequest) {
        imageService.modifyImage(id, imageRequest);
        return "Size was created successfully";
    }
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteImage(@PathVariable UUID id) {
        imageService.deleteImage(id);
        return "Size was created successfully";
    }
}
