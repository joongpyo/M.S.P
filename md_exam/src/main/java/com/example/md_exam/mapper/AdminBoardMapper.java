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
    @Insert("INSERT INTO ${board} VALUES(NULL, #{subject}, #{writer}, #{content}, 1, now(),1,1,1,#{isFiles},1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setAdminBoard(AdminBoardDto adminBoardDto);
    @Insert("INSERT INTO ${board}_files VALUES(#{id}, #{orgName}, #{savedFileName}, #{savedPathFileName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFile(FileDto fileDto);

    //게시판 List view
    @Select("SELECT * FROM ${board} ORDER BY id DESC LIMIT #{startNum}, #{offset}")
    public List<AdminBoardDto> getBoardList(Map<String, Object> map);
    @Select("SELECT COUNT(*) FROM ${board}")
    public int getBoardCount(String board);
}

