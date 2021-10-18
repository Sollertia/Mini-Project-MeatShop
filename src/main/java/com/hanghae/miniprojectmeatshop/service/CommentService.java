package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.CommentRequestDto;
import com.hanghae.miniprojectmeatshop.model.Comment;
import com.hanghae.miniprojectmeatshop.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    @Transactional
    public void addcomment(CommentRequestDto requestDto) {
        Comment com = new Comment(requestDto);
        commentRepository.save(com);
        // Item item = itemreposi.findbyid(com.getitemid)
        // item.getcomlist.add(com)
        // 이런구문 차후에 추가해 줘야함.
    }

    @Transactional
    public void update(Long id, CommentRequestDto reqDto, String writer) {
        Comment com = commentRepository.findById(id).orElse(null);
        if(com.getWriter().equals(writer))
        {
            com.setContent(reqDto.getContent());
            commentRepository.save(com);
        }
        else // 작성자가 아닌사람이 수정요청한것임.. 여기엘스문은 차후에 요청있으면 추가해주자자 예외구문을추가하던해서
        {    // 추가해주자
           return;
        }
    }

    @Transactional
    public void delete(Long id, String writer) {
        Comment com = commentRepository.findById(id).orElse(null);

        //삭제시에도 연관관계가 묶여있기때문에
        // Item item = itemreposito.findbyid(com.getitemid())
        // item.getcommentlist().remove(com)
        // 위와같은 구문 필요함

        if(com.getWriter().equals(writer))
        {
            commentRepository.delete(com);
        }
        else // 작성자가 아닌사람이 삭제요청한것임.. 여기엘스문은 차후에 요청있으면 추가해주자자 예외구문을추가하던해서
        {    // 추가해주자
            return;
        }

    }
}
