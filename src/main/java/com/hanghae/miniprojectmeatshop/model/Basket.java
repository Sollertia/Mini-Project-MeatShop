package com.hanghae.miniprojectmeatshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Basket {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false,name = "optionplus")
    private String option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Item_id")
    @JsonIgnore
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    // 장바구니에 처음 등록할 때
    public Basket(Item item, User user, int amount, String option){
           this.item = item;
           this.user = user;
           this.amount = amount;
           this.option = option;
    }

    // 장바구니 중복확인 후 amount 더해주기
    public void basketAmountUp(int amount){
        this.amount += amount;
    }

    // option이 같다면 같은 상품이기 때문에 현재수량에서 추가된 수량을 더해준다.
    public boolean checkAmountUp(User user, Item item, String option) {
        if(this.user.getId().equals(user.getId()) && this.item.getId().equals(item.getId()) && this.option.equals(option)){
            return true; // 중복일 경우 true 반환 - 이후 amount 증가 해야함.
        }
        return false; // 중복이 아닐 경우 false 반환
    }
}
