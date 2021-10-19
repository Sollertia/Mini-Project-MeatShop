package com.hanghae.miniprojectmeatshop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemDetailResponseDto;
import com.hanghae.miniprojectmeatshop.dto.ItemUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


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

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int defaultprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "item")
    private List<Basket> baskets = new ArrayList<>();


    @Column
    private String sumImgUrl;

    @Column
    private String detailImgUrl;


    public Item(ItemCreateRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.defaultprice = requestDto.getDefaultprice();
        this.sumImgUrl = requestDto.getSumImgUrl();
        this.user = user;
        this.detailImgUrl = requestDto.getDetailImgUrl();
    }


    public static Item of(ItemCreateRequestDto requestDto, User user){
        return new Item(requestDto, user);
    }


    public boolean isWritedBy(User user) {
        return this.user.equals(user);
    }

    public void update(ItemUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.category = requestDto.getCategory();
        this.defaultprice = requestDto.getDefaultprice();

    }

    public ItemDetailResponseDto ItemDetailResponseDtoToString(UserDetails userDetails) {
        DecimalFormat df = new DecimalFormat("###,###");
        String money = df.format(defaultprice);
        String detailMoney = df.format(defaultprice / 5);
        if (userDetails == null) {
            return ItemDetailResponseDto.builder()
                    .itemId(this.id)
                    .title(this.title)
                    .category(this.category)
                    .defaulpricetostring("기준가 " + money + "원 (500g) ")
                    .detailpricestring("100g 당" + detailMoney + "원")
                    .sumImgUrl(this.sumImgUrl)
                    .detailImgUrl(this.detailImgUrl)
                    .build();
        } else {
            return ItemDetailResponseDto.builder()
                    .itemId(this.id)
                    .title(this.title)
                    .category(this.category)
                    .defaulpricetostring("기준가 " + money + "원 (500g) ")
                    .detailpricestring("100g 당" + detailMoney + "원")
                    .sumImgUrl(this.sumImgUrl)
                    .detailImgUrl(this.detailImgUrl)
                    .build();
        }

    }

}
