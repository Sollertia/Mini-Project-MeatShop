package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.dto.BasketRequestDto;
import com.hanghae.miniprojectmeatshop.dto.BasketResponseDto;
import com.hanghae.miniprojectmeatshop.security.UserDetailsImpl;
import com.hanghae.miniprojectmeatshop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BasketController {

    private final BasketService basketService;

    // 장바구니 등록
    @PostMapping("/basket/insert")
    public List<BasketResponseDto> basketInsert(@RequestBody BasketRequestDto basketRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.basketInsert(basketRequestDto,userDetails);
    }

    // 장바구니 조회
    @GetMapping("/basket")
    public List<BasketResponseDto> basketList( @AuthenticationPrincipal UserDetailsImpl userDetails){
        return basketService.basketList(userDetails);
    }

    // 장바구니 삭제
    @PostMapping("/basket/delete")
    public void basketDelete(@RequestBody BasketRequestDto basketRequestDto){
        basketService.basketDelete(basketRequestDto.getBasketId());
    }

}
