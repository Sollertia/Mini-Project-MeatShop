package com.hanghae.miniprojectmeatshop.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;

@Getter
@RequiredArgsConstructor
public class ImgResponseDto {

    private final Path sumImgUrl;
    private final Path detailImgUrl;


}
