package com.ua.vinn.GammaLight.dto.request;

import lombok.Data;

@Data
public class AuthenticationUserRequestDto {
    private String email;
    private String password;
}
