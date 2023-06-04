package com.ua.vinn.GammaLight.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUserRequestDto{
    @NotBlank
    @Size(min=2, max=20)
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min=2, max=40)
    private String password;

    private MultipartFile avatar;

}
