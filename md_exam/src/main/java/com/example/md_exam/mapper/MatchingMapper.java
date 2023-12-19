package com.example.md_exam.mapper;

import com.example.md_exam.dto.MedicineDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MatchingMapper {

    @Select("select * from medicine where med_pregnant = #{medPregnant} and med_age = #{medAge} and med_dis = #{medDis} and med_type = #{medType};")
    public MedicineDto setMatching(MedicineDto medicineDto);

    @Select("select * from medicine where medId = #{medId}")
    public MedicineDto getMatchResult(MedicineDto medicineDto);

}
