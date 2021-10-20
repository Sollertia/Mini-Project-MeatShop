package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.BasketRequestDto;
import com.hanghae.miniprojectmeatshop.dto.BasketResponseDto;
import com.hanghae.miniprojectmeatshop.model.Basket;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.repository.BasketRepository;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import com.hanghae.miniprojectmeatshop.repository.UserRepository;
import com.hanghae.miniprojectmeatshop.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public BasketService(BasketRepository basketRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }



    // 장바구니 등록
    public List<BasketResponseDto> basketInsert(BasketRequestDto basketRequestDto,
                                                UserDetailsImpl userDetails) {

        User user = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("현재 로그인이 되어 있지 않습니다.")
        );

        // Item 과 User 유효성 검사
        Item item = itemRepository.findById(basketRequestDto.getItemId()).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
        );


        List<BasketResponseDto> list = new ArrayList<>(); // 클라이언트에 보내줄 DTO 생성


        Basket basket = basketRepository.findByUserAndItemAndOption(user, item,basketRequestDto.getOption()); // 중복된 장바구니 확인 용
        if(basket != null) { // 이미 장바구니에 동일한 user, item 이 있으면서 option까지 전부 동일한 basket 찾아서 수량만 증가시키기
                basket.basketAmountUp(basketRequestDto.getAmount());
                basketRepository.save(basket); // persist 등 공부해서 고민해보기
                List<Basket> baskets = basketRepository.findAllByUser(user);
                for (Basket b : baskets) {
                    list.add(BasketResponseDto.builder().basketId(b.getId()).itemId(b.getItem().getId()).
                            amount(b.getAmount()).option(b.getOption()).defaultprice(b.getItem().getDefaultprice()).sumImgUrl(b.getItem().getSumImgUrl()).build());
                }
                return list;
        }

        // 처음 장바구니에 저장하거나 장바구니의 상품은 같지만 option 이 다를 경우 새롭게 저장
        Basket newBasket = new Basket(item,user, basketRequestDto.getAmount(),basketRequestDto.getOption());
        basketRepository.save(newBasket);

        List<Basket> baskets = basketRepository.findAllByUser(user);
        for (Basket b : baskets) {
            list.add(BasketResponseDto.builder().basketId(b.getId()).itemId(b.getItem().getId()).
                    amount(b.getAmount()).option(b.getOption()).sumImgUrl(b.getItem().getSumImgUrl()).defaultprice(b.getItem().getDefaultprice()).build());
        }
        return list;
    }



    // 장바구니 조회
    public List<BasketResponseDto> basketList(UserDetailsImpl userDetails) {

        User user = userRepository.findByUserName(userDetails.getUsername()).orElseThrow(
                () -> new NullPointerException("현재 로그인이 되어 있지 않습니다.")
        );

        List<BasketResponseDto> list = new ArrayList<>(); // 클라이언트에 보내줄 DTO 생성

        List<Basket> baskets = basketRepository.findAllByUser(user);
        for (Basket b : baskets) {
            list.add(BasketResponseDto.builder().basketId(b.getId()).itemId(b.getItem().getId()).amount(b.getAmount())
                    .option(b.getOption()).sumImgUrl(b.getItem().getSumImgUrl()).defaultprice(b.getItem().getDefaultprice()).build());
        }
        return list;
    }


    // 장바구니 삭제
    public void basketDelete(Long basketId) {
            Basket baseket = basketRepository.findById(basketId).orElseThrow(
                    () -> new NullPointerException("해당 장바구니를 찾을 수 없습니다.")
            );
            basketRepository.delete(baseket);
    }
}


