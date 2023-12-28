package com.example.project.service;

import com.example.project.dto.DiseaseDto;
import com.example.project.dto.PageDto;
import com.example.project.dto.UserDto;
import com.example.project.mapper.DiseaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public PageDto PageInfo(int page, String searchType, String words) {
        PageDto pageDto = new PageDto();
        String searchQuery = getDisSearch(searchType,words);
        //전체 사용자 수
        int totalCount = diseaseMapper.getDisCount(searchQuery);
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
    public List<DiseaseDto> getDisList(int page,String searchType,String words){
        PageDto pd = PageInfo(page,searchType,words);
        Map<String, Object> map = new HashMap<>();
        String searchQuery = getDisSearch(searchType,words);
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("searchQuery",searchQuery);
        return diseaseMapper.getDisList(map);
    }

    public String getDisSearch(String searchType, String words){
        String searchQuery = "";
        if(searchType.equals("disName") ){
            searchQuery = " WHERE dis_name = '"+words+"'";
        }else if(searchType.equals("disSym")){
            searchQuery = " WHERE dis_sym LIKE '%"+words+"%'";
        }else {
            searchQuery = "";
        }
        return searchQuery;
    }
    public void deleteDis(DiseaseDto diseaseDto){
        diseaseMapper.deleteDis(diseaseDto);
    }

    //update
    public DiseaseDto viewDis(int disId){
       return diseaseMapper.viewDis(disId);
    }
    public void updateDis(DiseaseDto diseaseDto){
        diseaseMapper.updateDis(diseaseDto);
    }
}
