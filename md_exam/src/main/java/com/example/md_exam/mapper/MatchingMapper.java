package com.example.md_exam.mapper;

import com.example.md_exam.dto.MedicineDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MatchingMapper {

    @Select("select med_name, med_dis, med_eff, med_type, med_com, med_store from medicine where med_pregnant = 'N' and med_age = 2 and med_dis = '감기' and med_type = '알약';")
    public void setMatching(MedicineDto medicineDto);
}
