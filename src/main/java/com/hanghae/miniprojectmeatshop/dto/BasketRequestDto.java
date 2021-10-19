package com.hanghae.miniprojectmeatshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequestDto {
    private Long itemId;
    private int amount;
    private String option;
    private Long basketId;
}
