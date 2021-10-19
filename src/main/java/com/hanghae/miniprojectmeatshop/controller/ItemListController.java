package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.dto.ItemListResponseDto;
import com.hanghae.miniprojectmeatshop.service.ItemListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemListController {

    private final ItemListService itemListService;

    @GetMapping("/")
    public List<ItemListResponseDto> mainList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 2) Pageable pageable){
        return itemListService.mainList(pageable);
    }

    @GetMapping("/category")
    public List<ItemListResponseDto> categoryList(@RequestParam String category){
        return itemListService.categoryList(category);
    }

}
