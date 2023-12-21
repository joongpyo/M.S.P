package com.example.md_exam.service;

import com.example.md_exam.dto.DiseaseDto;
import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.PageDto;
import com.example.md_exam.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicineService {
    @Autowired
    MedicineMapper medicineMapper;
    public void setMedUpdate(MedicineDto medicineDto){
        medicineMapper.setMedUpdate(medicineDto);
    }
    public void setFile(FileDto fileDto){
        medicineMapper.setFile(fileDto);
    }
    public PageDto PageInfo(int page) {
        PageDto pageDto = new PageDto();
        //전체 사용자 수
        int totalCount = medicineMapper.getMedCount();
        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage = ((int) (Math.ceil((double) page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;
        if (endPage > totalPage) {
            endPage = totalPage;
        }
        pageDto.setStartNum((page - 1) * pageDto.getPageCount());
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        return pageDto;
    }
    public List<MedicineDto> getMedList(int page){
        PageDto pd = PageInfo(page);
        Map<String, Object> map = new HashMap<>();

        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());

        return medicineMapper.getMedList(map);
    }

}
