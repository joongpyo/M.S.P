package com.example.md_exam.mapper;

import com.example.md_exam.dto.CommentDto;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO qna_comment VALUES(NULL, #{cWriter}, #{cComment}, now(), #{bIdFk})")
    void setComment(CommentDto commentDto);

    @Select("SELECT * FROM qna_comment WHERE b_id_fk = ${bIdFk}")
    List<CommentDto> getCommentList(@ModelAttribute CommentDto commentDto);

    @Update("UPDATE qna SET comment_count= comment_count+1 WHERE id = #{bIdFk}")
    void updateCommentCnt(int id);

    @Delete("DELETE FROM qna_comment WHERE b_id_fk = #{bIdFk}")
    void setCommentDelete(int id);

}
