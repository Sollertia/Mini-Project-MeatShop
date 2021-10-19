package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.dto.CommentRequestDto;
import com.hanghae.miniprojectmeatshop.model.Comment;
import com.hanghae.miniprojectmeatshop.security.UserDetailsImpl;
import com.hanghae.miniprojectmeatshop.service.CommentService;
import com.hanghae.miniprojectmeatshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RestController
public class CommentController {

    private final CommentService commentService;
    private final ItemService itemService;

    @PostMapping("/api/addcomment/{itemId}")
    public Comment addcomment(
            @RequestBody CommentRequestDto requestDto,
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    )
    {
        if (userDetails == null) // 에러메세지 주던가해야할듯?
            return null;
        requestDto.setPostId(itemId);
        requestDto.setWriter(userDetails.getUser().getUserName());
        return commentService.addcomment(requestDto);
    }
    //댓글 조회
    //Item내부에 달린 댓글을 불러와야하므로 저 Path에들어가는 id는
    //Item의 PK이고, Comment의 PK가 아니다.
    // 이쪽을 Item Entity 생성후 다시작성
    @GetMapping("/api/comment/{postId}")
    public List<CommentRequestDto> getComment(@PathVariable Long postId)
    {
        return commentService.getComment(postId);
    }

    //수정
    @PutMapping("/api/updatecomment/{id}")
    public void updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto reqDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if(userDetails == null) // 이것도 요청이있으면 추가해주자..
            return;
        commentService.update(id, reqDto,userDetails.getUser().getUserName());
    }
    //삭제
    @GetMapping("/api/deletecomment/{id}")
    public void deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        commentService.delete(id,userDetails.getUser().getUserName());
    }

}
