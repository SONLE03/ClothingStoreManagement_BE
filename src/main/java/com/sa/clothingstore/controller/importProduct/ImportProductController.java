package com.sa.clothingstore.controller.importProduct;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.service.importProduct.ImportProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(APIConstant.IMPORTS)
@RestController
@AllArgsConstructor
public class ImportProductController {
    ImportProductService importService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createImport(@RequestBody List<@Valid ImportRequest> request){
        importService.createImport(request);
        return "Import created successfully";
    }
}
