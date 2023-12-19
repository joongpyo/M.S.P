package com.example.md_exam.service;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.mapper.MatchingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {
    @Autowired
    MatchingMapper matchingMapper;

    public MedicineDto setMatching(MedicineDto medicineDto){
       MedicineDto md = matchingMapper.setMatching(medicineDto);
        return md;
    }

    public MedicineDto getMatchResult(MedicineDto medicineDto){
        MedicineDto md = matchingMapper.getMatchResult(medicineDto);
        System.out.println(md);
        return md;
    }


}
