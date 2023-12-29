package com.example.project.mapper;

import com.example.project.dto.FileDto;
import com.example.project.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardQnaMapper {
    @Insert("INSERT INTO qna VALUES(null, #{subject}, #{writer}, #{content}, 0, now(), 1, 1, 1, #{isFiles}, 0, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void setBoard(QnaDto qnaDto);

    @Select("SELECT * FROM qna ${searchQuery} ORDER BY id DESC, seq ASC LIMIT #{startNum}, #{offset} ")
    List<QnaDto> getBoardQnA(Map<String,Object> map);

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

    @Update("UPDATE qna SET comment_count= comment_count+1 WHERE id = #{id}")
    void updateCommentCnt(int id);
}
