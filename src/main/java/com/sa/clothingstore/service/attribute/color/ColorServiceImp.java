package com.sa.clothingstore.service.attribute.color;

import com.sa.clothingstore.common.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.common.exception.ObjectNotFoundException;
import com.sa.clothingstore.dto.request.attribute.ColorRequest;
import com.sa.clothingstore.dto.request.attribute.ImageRequest;
import com.sa.clothingstore.dto.response.attribute.ColorResponse;
import com.sa.clothingstore.dto.response.attribute.ImageResponse;
import com.sa.clothingstore.model.attribute.Color;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.repository.attribute.ColorRepository;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ColorServiceImp implements ColorService{
    private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;
    @Override
    public ColorResponse createColor(ColorRequest colorRequest) {
        Optional<Color> existingColor = colorRepository.findByName(colorRequest.getName());
        if (existingColor.isPresent()) {
            throw new ObjectAlreadyExistsException("Color already exists");
        }
        return modelMapper
                .map(colorRepository
                                .save(modelMapper
                                        .map(colorRequest, Color.class))
                        , ColorResponse.class);
    }

    @Override
    public Color modifyColor(Integer id, ColorRequest colorRequest) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Color not found")
                );
        String newName = colorRequest.getName();
        if(colorRequest.getName().equals(newName)){
            throw new ObjectAlreadyExistsException("Color already existed");
        }
        color.setName(newName);
        return colorRepository.save(color);
    }

    @Override
    public void deleteColor(Integer id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Color not found")
                );
        colorRepository.delete(color);
    }

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
}
