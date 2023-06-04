package com.ua.vinn.GammaLight.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CommentRequestDto {

    @NotBlank
    @Size(min=1, max=500)
    private String comment;

}
