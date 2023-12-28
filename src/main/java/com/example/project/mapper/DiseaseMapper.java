package com.example.project.mapper;

import com.example.project.dto.DiseaseDto;
import com.example.project.dto.UserDto;
import org.apache.ibatis.annotations.*;

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

    //Update Query
    @Select("SELECT * FROM disease WHERE dis_id = #{disId}")
    public DiseaseDto viewDis(int disId);
    @Update("UPDATE disease SET dis_name = #{disName}, dis_sym = #{disName}, dis_reg = now() WHERE dis_id = #{disId}")
    public DiseaseDto updateDis();

    // DELETE QUERY
    @Delete("DELETE FROM disease WHERE dis_id = #{disId}")
    public void deleteDis(DiseaseDto diseaseDto);

}
