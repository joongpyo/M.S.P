package com.example.md_exam.service;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.mapper.MatchingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {
    @Autowired
    MatchingMapper matchingMapper;

    public void setMatching(MedicineDto medicineDto){
        matchingMapper.setMatching(medicineDto);
    }

}
