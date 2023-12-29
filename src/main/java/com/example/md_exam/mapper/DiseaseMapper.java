package com.example.md_exam.mapper;


import com.example.md_exam.dto.DiseaseDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiseaseMapper {

    @Select("SELECT COUNT(*) FROM disease WHERE dis_name = #{disName} ")
    public int getCheckDisName(String disName);
    @Insert("INSERT INTO disease VALUES(NULL, #{disName}, #{disSym}, now())")
    public void setDisease(DiseaseDto diseaseDto);
    @Select("SELECT * FROM disease ${searchQuery} ORDER BY dis_id DESC LIMIT #{startNum}, #{offset}")
    public List<DiseaseDto> getDisList(Map<String, Object> map);
    @Select("SELECT COUNT(*) FROM disease ${searchQuery}")
    public int getDisCount(String searchQuery);

    // DELETE QUERY
    @Delete("DELETE FROM disease WHERE dis_id = #{disId}")
    public void deleteDis(DiseaseDto diseaseDto);

}
