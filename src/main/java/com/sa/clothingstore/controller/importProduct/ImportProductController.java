package com.sa.clothingstore.controller.importProduct;

import com.sa.clothingstore.constant.APIConstant;
import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.service.importProduct.ImportProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(APIConstant.IMPORTS)
@RestController
@AllArgsConstructor
public class ImportProductController {
    private final ImportProductService importService;

    @GetMapping()
    public List<ImportInvoice> getAllImport(){
        return importService.getAllImport();
    }

    @GetMapping(APIConstant.IMPORT_ID)
    public List<ImportItem> getImportById(@PathVariable UUID importId){
        return importService.getImportById(importId);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String createImport(@RequestBody List<@Valid ImportRequest> request){
        importService.createImport(request);
        return "Import created successfully";
    }
}
