package com.hanghae.miniprojectmeatshop.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ItemCreateRequestDto {

    private final String title;
    private final String category;
    private final int defaultprice;
    private final String sumImgUrl;
    private String detailImgUrl;



}
