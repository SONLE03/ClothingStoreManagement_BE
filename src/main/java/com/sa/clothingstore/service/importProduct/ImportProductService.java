package com.sa.clothingstore.service.importProduct;

import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;

import java.util.List;
import java.util.UUID;

public interface ImportProductService {
    List<ImportInvoice> getAllImport();
    ImportResponse getImportById(UUID importId);
    void createImport(List<ImportRequest> importRequests);
    void printImport(UUID importId);
}
