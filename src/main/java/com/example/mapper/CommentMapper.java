package com.example.mapper;

import com.example.dto.CommentDto;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment_${configCode} VALUES(NULL, #{cWriter}, #{cComment}, now(), #{bIdFk})")
    void setComment(CommentDto commentDto);

    @Select("SELECT * FROM comment_${configCode} WHERE b_id_fk = ${bIdFk}")
    List<CommentDto> getCommentList(CommentDto commentDto);

    @Update("UPDATE board_${configCode} SET comment_count= comment_count+1 WHERE id = #{bIdFk}")
    void updateCommentCnt(String configCode,int bIdFk);

    @Delete("DELETE FROM comment_${configCode} WHERE b_id_fk = #{bIdFk}")
    void setCommentDelete(String configCode,int bIdFk);

}
