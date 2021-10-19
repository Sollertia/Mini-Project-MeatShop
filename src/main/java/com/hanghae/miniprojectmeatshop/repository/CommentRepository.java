package com.hanghae.miniprojectmeatshop.repository;

import com.hanghae.miniprojectmeatshop.dto.CommentRequestDto;
import com.hanghae.miniprojectmeatshop.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT NEW com.hanghae.miniprojectmeatshop.dto.CommentRequestDto(c.content,c.writer,c.createdAt,c.id)" +
            " FROM Comment as c" +
            " WHERE c.item.id = :itemId")
    List<CommentRequestDto> findByItemId(@Param("itemId") Long itemId);

}
