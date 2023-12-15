package com.example.md_exam.mapper;

import com.example.md_exam.dto.QnADto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardQnAMapper {
    @Insert("INSERT INTO qna VALUES(null, #{qnaSubject}, #{qnaWriter}, #{qnaContent}, 0, now(), 1, 1, 1, #{isFiles}, #{uIdFk})")
    void setBoard(QnADto qnADto);
}
