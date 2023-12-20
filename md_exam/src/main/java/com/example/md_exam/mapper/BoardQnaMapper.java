package com.example.md_exam.mapper;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardQnaMapper {
    @Insert("INSERT INTO qna VALUES(null, #{subject}, #{writer}, #{content}, 0, now(), 1, 1, 1, #{isFiles}, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void setBoard(QnaDto qnaDto);

    @Select("SELECT * FROM qna ${searchQuery} ORDER BY id DESC ")
    List<QnaDto> getBoardQnA(String searchQuery);

    @Select("SELECT COUNT(*) FROM qna ${searchQuery}")
    int getBoardCount(String searchQuery);

    @Select("SELECT * FROM qna WHERE id = #{id}")
    QnaDto getQnaView(int id);

    @Insert("INSERT INTO qnafiles VALUES(#{id},#{orgName},#{savedFileName},#{savedPathName},#{savedFileSize},#{folderName},#{ext})")
    void setFiles(FileDto fileDto);

    @Delete("DELETE FROM qna WHERE id = #{id}")
    void setDelete(int id);

    @Select("SELECT * FROM qnafiles WHERE id = #{id}")
    List<FileDto> getFile(int id);

    @Delete("DELETE FROM qnafiles WHERE id = #{id}")
    void setFilesDelete(int id);

    @Update("UPDATE qna SET subject=#{subject}, content=#{content}, isFiles=#{isFiles} WHERE id=#{id}")
    void setUpdate(QnaDto qnaDto);

    @Update("UPDATE qna SET visit = visit + 1 WHERE id = #{id}")
    void updateVisit(int id);
}
