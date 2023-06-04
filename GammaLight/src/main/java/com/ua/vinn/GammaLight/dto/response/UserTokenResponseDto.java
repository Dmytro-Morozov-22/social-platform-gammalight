package com.ua.vinn.GammaLight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTokenResponseDto {
    private String email;
    private String token;
}
