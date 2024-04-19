package com.sa.clothingstore.dto.response.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    @JsonProperty("id")
    private UUID category;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("product_gender")
    private UUID productGender;
}
