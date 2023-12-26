package com.example.project.mapper;

import com.example.project.dto.CommentDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO qna_comment VALUES(NULL, #{bId}, #{cWriter}, #{cComment}, now())")
    void setComment(CommentDto commentDto);

    @Select("SELECT * FROM qna_comment WHERE b_id = ${bId}")
    List<CommentDto> getCommentList(@ModelAttribute CommentDto commentDto);

}
