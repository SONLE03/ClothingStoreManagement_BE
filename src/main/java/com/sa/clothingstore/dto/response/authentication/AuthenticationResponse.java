package com.sa.clothingstore.dto.response.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationResponse extends CookieResponse {
    @JsonProperty("role")
    private String role;

    public AuthenticationResponse(Object accessCookie, Object refreshCookie, String role){
        super(accessCookie, refreshCookie);
        this.role = role;
    }
}
