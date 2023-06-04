package com.ua.vinn.GammaLight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String message;
    private LocalDateTime publicationDate;
    private List<String> pictures;
}
