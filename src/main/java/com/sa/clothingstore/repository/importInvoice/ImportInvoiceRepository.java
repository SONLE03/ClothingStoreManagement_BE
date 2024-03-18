package com.sa.clothingstore.repository.importInvoice;

import com.sa.clothingstore.model.importInvoice.ImportInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImportInvoiceRepository extends JpaRepository<ImportInvoice, UUID> {
}
