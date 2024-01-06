package com.example.project.service;

import com.example.project.dto.MyMedicineDto;
import com.example.project.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    MypageMapper mypageMapper;
    public void insertMyMed(MyMedicineDto myMedicineDto){
        mypageMapper.insertMyMed(myMedicineDto);
    }




}
