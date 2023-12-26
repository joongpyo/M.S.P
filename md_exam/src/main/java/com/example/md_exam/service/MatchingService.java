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

    public List<MedicineDto> setMatching(MedicineDto medicineDto){
        matchingMapper.setMatching(medicineDto);
        return matchingMapper.setMatching(medicineDto);
    }


    public List<MedicineDto> getMatchResult(MedicineDto medicineDto){
        matchingMapper.getMatchResult(medicineDto);
        return matchingMapper.getMatchResult(medicineDto);
    }


}
