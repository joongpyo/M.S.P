package com.example.md_exam.mapper;

import com.example.md_exam.dto.DiseaseDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DiseaseMapper {

    @Select("SELECT COUNT(*) FROM disease WHERE dis_name = #{disName} ")
    public int getCheckDisName(String disName);
    @Insert("INSERT INTO disease VALUES(NULL, #{disName}, #{disSym}, now())")
    public void setDisease(DiseaseDto diseaseDto);
}
