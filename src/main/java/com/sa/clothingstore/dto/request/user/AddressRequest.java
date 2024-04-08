package com.sa.clothingstore.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
    private String province;
    private String district;
    private String ward;
    private String specificAddress;
    private String postalCode;
}
