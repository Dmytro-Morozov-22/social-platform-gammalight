package com.ua.vinn.GammaLight.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
public class PostCreateRequestDto {
    @NotBlank
    @Size(min=2, max=500)
    private String postText;

    private List<MultipartFile> pictures;

    public PostCreateRequestDto() {}

    public PostCreateRequestDto(String postText, List<MultipartFile> pictures) {
        this.postText = postText;
        this.pictures = pictures;
    }
}
