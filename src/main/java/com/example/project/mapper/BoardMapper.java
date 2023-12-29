package com.example.project.mapper;

import com.example.project.dto.BoardDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    @Select("SELECT COUNT(*) FROM board_${configCode} ${searchQuery}")
    int getBoardCount(String configCode, String searchQuery);

    @Select("SELECT * FROM board_${configCode} ${searchQuery} ORDER BY board_type ASC, grp DESC, seq ASC LIMIT #{startNum}, #{offset} ")
    List<BoardDto> getBoard(Map<String,Object> map);

    @Select("SELECT * FROM board_${configCode} WHERE id = #{id}")
    BoardDto getView(String configCode, int id);

    @Select("SELECT * FROM files_${configCode} WHERE id = #{id}")
    List<FileDto> getFile(String configCode, int id);

    @Update("UPDATE board_${configCode} SET visit = visit + 1 WHERE id = #{id}")
    void updateVisit(String configCode,int id);

    @Select("SELECT IFNULL( MAX(grp) + 1,  1) FROM board_${configCode}")
    int getGrpMaxCnt(String configCode);

    @Insert("INSERT INTO board_${configCode} VALUES(null, #{subject}, #{writer}, #{content}, 0, now(), #{grp}, #{seq}, #{depth}, #{isFiles}, 0, #{boardType}, #{uIdFk})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void setBoard(BoardDto boardDto);

    @Insert("INSERT INTO files_${configCode} VALUES(#{id},#{orgName},#{savedFileName},#{savedPathName},#{savedFileSize},#{folderName},#{ext})")
    void setFiles(FileDto fileDto);

    @Select("SELECT * FROM board_${configCode} WHERE grp = #{grp} AND depth > #{depth} ")
    List<BoardDto> getDeleteList(BoardDto boardDto);

    @Delete("DELETE FROM files_${configCode} WHERE id = #{id}")
    void setFilesDelete(String configCode,int id);

    @Delete("DELETE FROM board_${configCode} WHERE id = #{id}")
    void setDelete(String configCode,int id);
    @Update("UPDATE board_${configCode} SET seq=seq+1 WHERE grp = #{grp} AND seq > #{seq}")
    void setReplyUpdate(BoardDto boardDto);

    @Delete("DELETE FROM board_${configCode} WHERE grp = #{grp} AND depth > #{depth}")
    void setReplyDelete(BoardDto boardDto);

    @Update("UPDATE board_${configCode} SET subject=#{subject}, content=#{content}, isFiles=#{isFiles} WHERE id=#{id}")
    void setUpdate(BoardDto boardDto);

    @Select("SELECT *FROM board_list")
    List<MedicineDto> getMed();

    @Select("SELECT *FROM files_list")
    List<FileDto> getMedFiles();

}



