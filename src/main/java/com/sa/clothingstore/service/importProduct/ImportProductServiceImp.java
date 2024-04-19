package com.sa.clothingstore.service.importProduct;

import com.sa.clothingstore.dto.request.importProduct.ImportRequest;
import com.sa.clothingstore.dto.response.importProduct.ImportResponse;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.importInvoice.ImportItemKey;
import com.sa.clothingstore.repository.importInvoice.ImportInvoiceRepository;
import com.sa.clothingstore.repository.importInvoice.ImportItemRepository;
import com.sa.clothingstore.repository.product.ProductItemRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImportProductServiceImp implements ImportProductService{
    private final EntityManager entityManager;
    private final UserDetailService userDetailsService;
    private final ProductItemRepository productItemRepository;

    private final ImportInvoiceRepository importInvoiceRepository;
    private final ImportItemRepository importItemRepository;
    @Override
    public List<ImportInvoice> getAllImport() {
        return importInvoiceRepository.findAll();
    }

    @Override
    public ImportResponse getImportById(UUID importId) {
        if(!importInvoiceRepository.existsById(importId))
            new ObjectNotFoundException("Import not found");
        return null;
    }

    @Override
    @Transactional
    public void createImport(List<ImportRequest> importRequests) {
        // Create a new ImportInvoice
        ImportInvoice importInvoice = new ImportInvoice();
        importInvoice.setCommonCreate(userDetailsService.getIdLogin());
        importInvoice = importInvoiceRepository.save(importInvoice);

        // Initialize total to zero
        BigDecimal total = BigDecimal.ZERO;

        List<ImportItem> importItems = new ArrayList<>();

        for (ImportRequest request : importRequests) {
            UUID productItemId = request.getProductItemId();

            // Check if the product exists
            if (!productItemRepository.existsById(productItemId)) {
                throw new ObjectNotFoundException("Product not found");
            }
            // Trigger giá nhập < giá bán
            // Calculate total for each import request
            total = total.add(request.getTotal());

            // Create ImportItemKey
            ImportItemKey importItemKey = new ImportItemKey();
            importItemKey.setImportId(importInvoice.getId()); // Set importId from the saved ImportInvoice
            importItemKey.setProductItemId(productItemId); // Set productItemId

            // Create ImportItem
            ImportItem importItem = new ImportItem();
            importItem.setId(importItemKey);
            importItem.setImportInvoice(importInvoice);
            importItem.setProductItem(productItemRepository.getById(productItemId));
            importItem.setQuantity(request.getQuantity());
            importItem.setPrice(request.getPrice());
            importItem.setTotal(request.getTotal());

            importItems.add(importItem);
        }

        // Set total for ImportInvoice
        importInvoice.setTotal(total);

        // Save ImportItems
        importItemRepository.saveAll(importItems);
    }

    @Override
    public void printImport(UUID importId) {

    }
}
