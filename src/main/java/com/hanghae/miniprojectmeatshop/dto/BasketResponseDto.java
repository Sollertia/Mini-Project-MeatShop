package com.hanghae.miniprojectmeatshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class BasketResponseDto {
    private Long basketId;
    private Long itemId;
    private int amount;
    private String option;
    private String sumImgUrl;
}
