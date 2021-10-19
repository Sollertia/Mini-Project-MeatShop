package com.hanghae.miniprojectmeatshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ItemListResponseDto {

    private Long itemId;
    private String title;
    private String category;
    private int defaultprice;
    private String sumImgUrl;
}
