package com.example.md_exam.mapper;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardQnaMapper {
    @Insert("INSERT INTO qna VALUES(null, #{qnaSubject}, #{qnaWriter}, #{qnaContent}, 0, now(), 1, 1, 1, #{isFiles}, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "qnaId")
    void setBoard(QnaDto qnADto);

    @Select("SELECT * FROM qna ORDER BY qna_id DESC")
    List<QnaDto> getQnaList();

    @Select("SELECT * FROM qna WHERE qna_id = #{qnaId}")
    QnaDto getQnaView(int qnaId);
    @Insert("INSERT INTO qnafiles VALUES(#{id},#{orgName},#{savedFileName},#{savedPathName},#{savedFileSize},#{folderName},#{ext})")
    void setFiles(FileDto fileDto);
}
