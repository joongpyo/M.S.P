package com.example.project.mapper;

import com.example.project.dto.DiseaseDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MedicineMapper {
    @Insert("INSERT INTO medicine VALUES(NULL, #{medName}, #{medDis}, #{medEff}, #{medType}, #{medStore}, #{medCom}, #{medAge}, #{medPregnant}, now(), #{isFiles})")
    @Options(useGeneratedKeys = true, keyProperty = "medId")
    public void setMedInsert(MedicineDto medicineDto);
    @Insert("INSERT INTO files_admin VALUES(#{id}, #{orgName}, #{savedFileName}, #{savedPathName}, #{savedFileSize}, #{folderName}, #{ext})")
    public void setFile(FileDto fileDto);
    @Select("SELECT * FROM medicine ${searchQuery} ORDER BY med_id DESC LIMIT #{startNum}, #{offset}")
    public List<MedicineDto> getMedList(Map<String, Object> map);
    @Select("SELECT COUNT(*) FROM medicine ${searchQuery}")
    public int getMedCount(String searchQuery);
    @Select("SELECT * FROM files_admin WHERE id = #{id}")
    public List<FileDto> getFiles(int id);
    @Select("SELECT * FROM medicine WHERE med_id=#{medId}")
    public MedicineDto getMedView(int medId);
    @Select("SELECT * FROM files_admin WHERE id = #{medId}")
    public FileDto getFileView(int medId);

    //medicine delete
    @Delete("DELETE FROM medicine WHERE med_id = #{medId}")
    public void deleteMed(MedicineDto medicineDto);
    @Delete("DELETE FROM files_admin WHERE id = #{id}")
    public void setFileDelete(int id);

    //medicine update
    @Update("UPDATE medicine SET med_dis = #{medDis}, med_eff = #{medEff},med_type = #{medType}, med_store = #{medStore}, med_com = #{medCom}, med_age = #{medAge}, med_pregnant = #{medPregnant}, med_reg = now(), is_files = #{isFiles} WHERE med_id = #{medId}")
    @Options(useGeneratedKeys = true, keyProperty = "medId")
    public void medUpdate(MedicineDto medicineDto);

}
