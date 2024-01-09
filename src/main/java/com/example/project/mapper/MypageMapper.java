package com.example.project.mapper;

import com.example.project.dto.MedicineDto;
import com.example.project.dto.MyMedicineDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.*;

@Mapper
public interface MypageMapper {

    @Insert("insert into myMedicine values(null, #{medId}, #{uId}) ")
    public void insertMyMed(MyMedicineDto myMedicineDto);

    @Select("select * from myMedicine where u_id = #{uId}")
    public List<MyMedicineDto> myMedList(int uId);

    @Select("select * from medicine where med_id = #{medId}")
    public MedicineDto medicineList(int medId);

}