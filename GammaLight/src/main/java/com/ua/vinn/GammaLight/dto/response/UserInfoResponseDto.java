package com.ua.vinn.GammaLight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponseDto {
    private Long id;
    private String firstName;
    private String email;
    private String token;
}
