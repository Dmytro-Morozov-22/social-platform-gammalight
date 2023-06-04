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
public class SponsoredPostCreateDto extends PostCreateRequestDto {

    @NotBlank
    private long sponsorId;

    public SponsoredPostCreateDto() {}

    public SponsoredPostCreateDto(String postText, List<MultipartFile> pictures, long sponsorId) {
        super(postText, pictures);
        this.sponsorId = sponsorId;
    }

}
