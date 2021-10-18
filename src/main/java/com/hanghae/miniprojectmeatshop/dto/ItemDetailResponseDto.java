package com.hanghae.miniprojectmeatshop.dto;


import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ItemDetailResponseDto {
    private Long ItemId;
    private String title;
    private String category;
    private int defaultprice;
    private int detailprice;
    private String sumImgUrl;
    private String detailImgUrl;
}
