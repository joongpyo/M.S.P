package com.example.project.mapper;

import com.example.project.dto.AdminBoardDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminBoardMapper {
    @Select("SELECT IFNULL( (MAX(grp) + 1), 1) FROM board_${configCode}")
    public int getGrpMaxCnt(String configCode);
    @Insert("INSERT INTO board_${configCode} VALUES(NULL, #{subject}, #{writer}, #{content}, 0, now(), #{grp}, 1, 1,#{isFiles},0,1,1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void setAdminBoard(AdminBoardDto adminBoardDto);
    @Insert("INSERT INTO files_${configCode} VALUES(#{id}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFile(FileDto fileDto);

    //Notice view
    @Select("SELECT * FROM board_${configCode} ${searchQuery} ORDER BY id DESC LIMIT #{startNum}, #{offset}")
    public List<AdminBoardDto> getBoardList(Map<String, Object> map);
    @Select("SELECT COUNT(*) FROM board_${configCode} ${searchQuery}")
    public int getBoardCount(String configCode, String searchQuery);

    @Select("SELECT COUNT(*) FROM board_Notice WHERE DATE(reg) = CURDATE()")
    public int getNoticeCount();
    @Select("SELECT COUNT(*) FROM board_QnA WHERE DATE(reg) = CURDATE()")
    public int getQnaCount();
    @Select("SELECT COUNT(*) FROM board_Review WHERE DATE(reg) = CURDATE()")
    public int getReviewCount();
    @Select("SELECT COUNT(*) FROM board_Notice")
    public int getTotalNotice();
    @Select("SELECT COUNT(*) FROM board_QnA")
    public int getTotalQna();
    @Select("SELECT COUNT(*) FROM board_Review")
    public int getTotalReview();
}

