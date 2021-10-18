package com.hanghae.miniprojectmeatshop.model;


import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemDetailRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemDetailResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "CATEGORY")
    private String category;

    @Column(nullable = false, columnDefinition = "DEFAULTPRICE")
    private int defaultprice;

    @Column(nullable = false, columnDefinition = "DETAILPRICE")
    private int detailprice;

    @Column
    private String sumImgUrl;

    @Column
    private String detailImgUrl;


    public Item(ItemCreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.defaultprice = requestDto.getDefaultprice();
        this.detailprice = requestDto.getDetailprice();
        this.sumImgUrl = requestDto.getSumImgUrl();
        this.detailImgUrl = requestDto.getDetailImgUrl();
    }

    public Item(ItemDetailRequestDto requestDto) {
        this.id = requestDto.getItemId();
    }

    public ItemDetailResponseDto ItemResponseDto(){
        return ItemDetailResponseDto.builder()
                .ItemId(this.id)
                .title(this.title)
                .category(this.title)
                .defaultprice(this.defaultprice)
                .detailprice(this.detailprice)
                .sumImgUrl(this.sumImgUrl)
                .detailImgUrl(this.detailImgUrl)
                .build();
    }

}
