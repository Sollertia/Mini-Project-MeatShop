package com.hanghae.miniprojectmeatshop.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemCreateRequestDto {

    private String title;
    private String category;
    private int defaultprice;
    private int detailprice;
    private String sumImgUrl;
    private String detailImgUrl;

}
