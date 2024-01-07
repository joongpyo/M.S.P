package com.example.project.mapper;

import com.example.project.dto.MyMedicineDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface MypageMapper {

    @Insert("insert into myMedicine values(null, #{medId}, #{uId}) ")
    public void insertMyMed(MyMedicineDto myMedicineDto);

//    @Select("select med_id, med_name, med_dis, med_eff, med_type, med_store, med_com, med_age, med_pregnant from medicine where med_id = #{medId}")
}