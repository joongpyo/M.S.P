package com.example.md_exam.mapper;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardQnaMapper {
    @Insert("INSERT INTO qna VALUES(null, #{qnaSubject}, #{qnaWriter}, #{qnaContent}, 0, now(), 1, 1, 1, #{isFiles}, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "qnaId")
    void setBoard(QnaDto qnaDto);

    @Select("SELECT * FROM qna ORDER BY qna_id DESC")
    List<QnaDto> getQnaList();

    @Select("SELECT * FROM qna WHERE qna_id = #{qnaId}")
    QnaDto getQnaView(int qnaId);

    @Insert("INSERT INTO qnafiles VALUES(#{id},#{orgName},#{savedFileName},#{savedPathName},#{savedFileSize},#{folderName},#{ext})")
    void setFiles(FileDto fileDto);

    @Delete("DELETE FROM qna WHERE qna_id = #{qnaId}")
    void setDelete(int qnaId);

    @Select("SELECT * FROM qnafiles WHERE id = #{qnaId}")
    List<FileDto> getFile(int qnaId);

    @Delete("DELETE FROM qnafiles WHERE id = #{qnaId}")
    void setFilesDelete(int qnaId);

    @Update("UPDATE qna SET qna_subject=#{qnaSubject}, qna_content=#{qnaContent}, isFiles=#{isFiles} WHERE qna_id=qnaId")
    void setUpdate(QnaDto qnaDto);
}
