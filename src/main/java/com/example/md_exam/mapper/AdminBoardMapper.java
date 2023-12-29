package com.example.md_exam.mapper;


import com.example.md_exam.dto.AdminBoardDto;
import com.example.md_exam.dto.FileDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminBoardMapper {
    @Insert("INSERT INTO board_${configCode} VALUES(NULL, #{subject}, #{writer}, #{content}, 0, now(),1,1,1,#{isFiles},0,1,1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setAdminBoard(AdminBoardDto adminBoardDto);
    @Insert("INSERT INTO files_${configCode} VALUES(#{id}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFile(FileDto fileDto);

    //Notice view
    @Select("SELECT * FROM board_${configCode} ORDER BY id DESC LIMIT #{startNum}, #{offset}")
    public List<AdminBoardDto> getBoardList(Map<String, Object> map);
    @Select("SELECT COUNT(*) FROM board_${configCode}")
    public int getBoardCount(String configCode);

}

