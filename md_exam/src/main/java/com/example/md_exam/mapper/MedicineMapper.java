package com.example.md_exam.mapper;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.MedicineFileDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface MedicineMapper {
    @Insert("INSERT INTO medicine VALUES(NULL, #{medName}, #{medDis}, #{medEff}, #{medType}, #{medStore}, #{medCom}, #{medAge}, #{medPregnant}, now(), #{medIsFiles})")
    @Options(useGeneratedKeys = true, keyProperty = "medId")
    public void setMedUpdate(MedicineDto medicineDto);

    @Insert("INSET INTO admin_files VALUES(NULL, #{orgName}, #{savedFileName}, #{savedPathFileName}), #{savedFileSize}, #{folderName}, #{ext}")
    public void setFile(MedicineFileDto medicineFileDto);
}
