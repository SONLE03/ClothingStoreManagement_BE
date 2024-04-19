package com.sa.clothingstore.dto.response.importProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ImportResponse {
    @JsonProperty("importId")
    private UUID importId;
}
