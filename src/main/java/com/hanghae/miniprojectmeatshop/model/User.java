package com.hanghae.miniprojectmeatshop.model;

import com.hanghae.miniprojectmeatshop.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class User extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false,unique = true) // 이게 실질적인 ID
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Basket> baskets = new ArrayList<>();


    public User(UserRequestDto requestDto) {
        this.userName = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }
}
