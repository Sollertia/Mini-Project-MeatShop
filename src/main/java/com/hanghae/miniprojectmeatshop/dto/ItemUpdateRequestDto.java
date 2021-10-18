package com.hanghae.miniprojectmeatshop.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemUpdateRequestDto {
    private final String title;
    private final String category;
    private final int defaultprice;
    private final String  sumImgUrl;
    private final String detailImgUrl;
}


