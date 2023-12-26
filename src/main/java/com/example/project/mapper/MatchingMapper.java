package com.example.project.mapper;

import com.example.project.dto.MedicineDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MatchingMapper {

    @Select("select * from medicine where med_pregnant = #{medPregnant} and med_age = #{medAge} and med_dis like '%${medDis}%' and med_type = #{medType};")
    public List<MedicineDto> setMatching(MedicineDto medicineDto);
    //원하는 내용을 찾는 용

    @Select("select * from medicine where medId = #{medId}")
    public List<MedicineDto> getMatchResult(MedicineDto medicineDto);
    // 찾은 내용을 불러오기위해서 Pk값으로  정의 하는것

}
