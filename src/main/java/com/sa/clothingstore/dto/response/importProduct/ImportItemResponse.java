package com.sa.clothingstore.dto.response.importProduct;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa.clothingstore.model.product.ProductItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ImportItemResponse {
    @JsonProperty("productItem")
    private ProductItem productItem;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("total")
    private BigDecimal total;
}
