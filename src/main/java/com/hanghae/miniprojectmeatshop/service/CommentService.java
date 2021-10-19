package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.CommentRequestDto;
import com.hanghae.miniprojectmeatshop.model.Comment;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.repository.CommentRepository;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ItemRepository itemRepository;
    @Transactional
    public Comment addcomment(CommentRequestDto requestDto) {
        Comment com = new Comment(requestDto);
        Item item = itemRepository.findById(requestDto.getPostId()).orElse(null);
        if(item == null)
            return null;
        com.setItem(item);
        return commentRepository.save(com);
    }

    @Transactional
    public void update(Long id, CommentRequestDto reqDto, String writer) {
        Comment com = commentRepository.findById(id).orElse(null);
        if(com.getWriter().equals(writer))
        {
            com.setContent(reqDto.getContent());
            commentRepository.save(com);
        }
        else
        {
           return;
        }
    }

    @Transactional
    public void delete(Long id, String writer) {
        Comment com = commentRepository.findById(id).orElse(null);
        if(com.getWriter().equals(writer))
        {
            commentRepository.delete(com);
        }
        else
        {
            return;
        }

    }

    @Transactional
    public List<CommentRequestDto> getComment(Long postId) {
        List<CommentRequestDto> coms = commentRepository.findByItemId(postId);
        return coms;
    }

    //Test용 혹시 사용할수도있을거같아서 남겨놔요..
    public void mytest() {
        Item item1 = itemRepository.findById(2L).orElse(null);
        Item item2 = itemRepository.findById(3L).orElse(null);
        Item item3 = itemRepository.findById(4L).orElse(null);
        Comment com1 = new Comment();
        com1.setWriter("tmdwns1235");
        com1.setContent("내용1");
        com1.setItem(item1);
        Comment com2 = new Comment();
        com2.setWriter("tmdwns1235");
        com2.setContent("내용2");
        com2.setItem(item1);
        Comment com3 = new Comment();
        com3.setWriter("tmdwns1235");
        com3.setContent("내용3");
        com3.setItem(item2);
        Comment com4 = new Comment();
        com4.setWriter("tmdwns1235");
        com4.setContent("내용4");
        com4.setItem(item2);
        Comment com5 = new Comment();
        com5.setWriter("tmdwns1235");
        com5.setContent("내용5");
        com5.setItem(item3);
        Comment com6 = new Comment();
        com6.setWriter("tmdwns1235");
        com6.setContent("내용6");
        com6.setItem(item3);
        commentRepository.save(com1);
        commentRepository.save(com2);
        commentRepository.save(com3);
        commentRepository.save(com4);
        commentRepository.save(com5);
        commentRepository.save(com6);
    }
}
