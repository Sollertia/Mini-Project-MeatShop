package com.hanghae.miniprojectmeatshop.dto;

import com.hanghae.miniprojectmeatshop.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private Long postId;
    private Long id;

    public CommentRequestDto(Comment com)
    {
        this.content = com.getContent();
        this.writer = com.getWriter();
        this.createdAt = com.getCreatedAt();
    }
    public CommentRequestDto(String content,String writer, LocalDateTime createdAt, Long id)
    {
        this.content = content;
        this.writer = writer;
        this.createdAt= createdAt;
        this.id = id;
    }
}
