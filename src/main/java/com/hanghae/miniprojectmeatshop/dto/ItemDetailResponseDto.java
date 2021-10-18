package com.hanghae.miniprojectmeatshop.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Builder
public class ItemDetailResponseDto {

    private Long itemId;
    private String title;
    private String category;
    private String defaulpricetostring;
    private String detailpricestring;
    private String sumImgUrl;
    private String detailImgUrl;

}
