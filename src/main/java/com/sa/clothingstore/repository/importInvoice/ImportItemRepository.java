package com.sa.clothingstore.repository.importInvoice;

import com.sa.clothingstore.model.importInvoice.ImportItem;
import com.sa.clothingstore.model.importInvoice.ImportItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImportItemRepository extends JpaRepository<ImportItem, UUID> {
}
