package com.example.md_exam.mapper;

import com.example.md_exam.dto.AdminBoardDto;
import com.example.md_exam.dto.FileDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface AdminBoardMapper {
    @Insert("INSERT INTO ${board} VALUES(NULL, #{subject}, #{writer}, #{content}, 1, now(),1,1,1,#{isFiles},1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setAdminBoard(AdminBoardDto adminBoardDto);

    @Insert("INSERT INTO ${board}_files VALUES(#{id}, #{orgName}, #{savedFileName}, #{savedPathFileName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFile(FileDto fileDto);
}
