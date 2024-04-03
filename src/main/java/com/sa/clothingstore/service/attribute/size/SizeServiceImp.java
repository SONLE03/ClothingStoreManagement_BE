package com.sa.clothingstore.service.attribute.size;

import com.sa.clothingstore.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.dto.request.attribute.SizeRequest;
import com.sa.clothingstore.dto.response.attribute.SizeResponse;
import com.sa.clothingstore.model.attribute.Size;
import com.sa.clothingstore.repository.attribute.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeServiceImp implements SizeService{
    private final SizeRepository sizeRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAll();
    }
    @Override
    public SizeResponse createSize(SizeRequest sizeRequest) {
        Optional<Size> existingSize = sizeRepository.findByName(sizeRequest.getName());
        if (existingSize.isPresent()) {
            throw new ObjectAlreadyExistsException("Size already exists");
        }
        return modelMapper
                .map(sizeRepository
                                .save(modelMapper
                                        .map(sizeRequest, Size.class))
                        , SizeResponse.class);
    }

    @Override
    public Size modifySize(Integer id, SizeRequest sizeRequest) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Size not found")
                );
        String newName = sizeRequest.getName();
        if(sizeRequest.getName().equals(newName)){
            throw new ObjectAlreadyExistsException("Size already existed");
        }
        size.setName(sizeRequest.getName());
        return sizeRepository.save(size);
    }

    @Override
    public void deleteSize(Integer id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Size not found")
                );
        sizeRepository.delete(size);
    }
}
