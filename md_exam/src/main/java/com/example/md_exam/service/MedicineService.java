package com.example.md_exam.service;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.MedicineFileDto;
import com.example.md_exam.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
    @Autowired
    MedicineMapper medicineMapper;
    public void setMedUpdate(MedicineDto medicineDto){
        medicineMapper.setMedUpdate(medicineDto);
    }
    public void setFile(MedicineFileDto medicineFileDto){
        medicineMapper.setFile(medicineFileDto);
    }
}
