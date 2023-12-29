package com.example.service;


import com.example.dto.MedicineDto;
import com.example.mapper.MatchingMapper;
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
