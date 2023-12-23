package com.example.md_exam.mapper;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardQnaMapper {
    @Insert("INSERT INTO qna VALUES(null, #{subject}, #{writer}, #{content}, 0, now(), #{grp}, #{seq}, #{depth}, #{isFiles}, 0, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void setBoard(QnaDto qnaDto);

    @Select("SELECT * FROM ${boardCode} ${searchQuery} ORDER BY grp DESC, seq ASC LIMIT #{startNum}, #{offset} ")
    List<QnaDto> getBoardQnA(Map<String,Object> map);

    @Select("SELECT COUNT(*) FROM ${boardCode} ${searchQuery}")
    int getBoardCount(Map<String,Object> map);

    @Select("SELECT * FROM ${boardCode} WHERE id = #{id}")
    QnaDto getQnaView(String boardCode,int id);

    @Insert("INSERT INTO qna_files VALUES(#{id},#{orgName},#{savedFileName},#{savedPathName},#{savedFileSize},#{folderName},#{ext})")
    void setFiles(FileDto fileDto);

    @Select("SELECT * FROM qna WHERE grp = #{grp} AND depth > #{depth} ")
    List<QnaDto> getDeleteList(QnaDto qnaDto);

    @Delete("DELETE FROM qna WHERE id = #{id}")
    void setDelete(int id);

    @Select("SELECT * FROM ${boardCode}_files WHERE id = #{id}")
    List<FileDto> getFile(String boardCode,int id);

    @Delete("DELETE FROM qna_files WHERE id = #{id}")
    void setFilesDelete(int id);

    @Update("UPDATE qna SET subject=#{subject}, content=#{content}, isFiles=#{isFiles} WHERE id=#{id}")
    void setUpdate(QnaDto qnaDto);

    @Update("UPDATE qna SET visit = visit + 1 WHERE id = #{id}")
    void updateVisit(int id);


    @Select("SELECT IFNULL( MAX(grp) + 1,  1) FROM qna")
    int getGrpMaxCnt();

    @Update("UPDATE qna SET seq=seq+1 WHERE grp = #{grp} AND seq > #{seq}")
    void setReplyUpdate(QnaDto qnaDto);

    @Delete("DELETE FROM qna WHERE grp = #{grp} AND depth > #{depth}")
    void setReplyDelete(QnaDto qnaDto);
}
