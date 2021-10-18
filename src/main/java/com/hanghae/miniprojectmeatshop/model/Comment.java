package com.hanghae.miniprojectmeatshop.model;

import com.hanghae.miniprojectmeatshop.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class Comment extends Timestamped{
    // 단방향으로해도 상관없으니까 Item 엔티티가 생기면
    // 거기다 추가하던가 협의하자..
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false,unique = true) // 이게 실질적인 ID
    private String content;

    @Column(nullable = false)
    private String writer;

    // Item 엔티티티 Mappin 관계 지정해줘야할듯
    // 그래서 이부분은 일단 주석
//    @Column(nullable = false)
//    private Long ItemId;

    public Comment(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
    }
}
