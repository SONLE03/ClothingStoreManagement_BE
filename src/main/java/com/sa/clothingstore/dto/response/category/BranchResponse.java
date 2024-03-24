package com.sa.clothingstore.dto.response.category;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponse {
    private UUID id;
    private String name;
}
