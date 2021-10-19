package com.hanghae.miniprojectmeatshop.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
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
