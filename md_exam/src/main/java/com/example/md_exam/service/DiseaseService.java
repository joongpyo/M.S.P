package com.example.md_exam.service;

import com.example.md_exam.dto.DiseaseDto;
import com.example.md_exam.mapper.DiseaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiseaseService {
    @Autowired
    DiseaseMapper diseaseMapper;

    public int getCheckDisName(String disName){
        return diseaseMapper.getCheckDisName(disName);
    }
    public void setDisease(DiseaseDto diseaseDto){
        diseaseMapper.setDisease(diseaseDto);
    }

}
