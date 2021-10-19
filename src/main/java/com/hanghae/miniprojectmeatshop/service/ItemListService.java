package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.ItemListResponseDto;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemListService {

    private final ItemRepository itemRepository;

    // 메인 페이지 추천상품 카테고리별로 2개씩
    public List<ItemListResponseDto> mainList(Pageable pageable){

        List<ItemListResponseDto> list = new ArrayList<>(6);

        String category = "pork";
        List<Item> porks = itemRepository.findByCategory(category, pageable);
        for(Item p : porks){
            list.add(ItemListResponseDto.builder().itemId(p.getId()).title(p.getTitle()).category(p.getCategory()).
                    sumImgUrl(p.getSumImgUrl()).defaultprice(p.getDefaultprice()).build());
        }

        category = "beef";
        List<Item> beefs = itemRepository.findByCategory(category, pageable);
        for(Item b : beefs){
            list.add(ItemListResponseDto.builder().itemId(b.getId()).title(b.getTitle()).category(b.getCategory()).
                    sumImgUrl(b.getSumImgUrl()).defaultprice(b.getDefaultprice()).build());
        }

        category = "chicken";
        List<Item> chickens = itemRepository.findByCategory(category, pageable);
        for(Item c : chickens){
            list.add(ItemListResponseDto.builder().itemId(c.getId()).title(c.getTitle()).category(c.getCategory()).
                    sumImgUrl(c.getSumImgUrl()).defaultprice(c.getDefaultprice()).build());
        }

        return list;
    }

    // 카테고리 별 리스트
    public List<ItemListResponseDto> categoryList(String category) {

        List<ItemListResponseDto> list = new ArrayList<>();

        List<Item> items = itemRepository.findByCategory(category);

        for(Item i : items){
            list.add(ItemListResponseDto.builder().itemId(i.getId()).title(i.getTitle()).category(i.getCategory()).
                    sumImgUrl(i.getSumImgUrl()).defaultprice(i.getDefaultprice()).build());
        }
        return list;

    }



}
