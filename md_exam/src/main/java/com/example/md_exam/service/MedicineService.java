package com.example.md_exam.service;


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
    public PageDto PageInfo(int page, String searchType, String words) {
        PageDto pageDto = new PageDto();
        String searchQuery = getMedSearch(searchType,words);
        //전체 사용자 수
        int totalCount = medicineMapper.getMedCount(searchQuery);
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
    public List<MedicineDto> getMedList(int page,String searchType,String words){
        PageDto pd = PageInfo(page,searchType,words);
        Map<String, Object> map = new HashMap<>();
        String searchQuery = getMedSearch(searchType,words);
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("searchQuery",searchQuery);

        return medicineMapper.getMedList(map);
    }
    public String getMedSearch(String searchType, String words){
        String searchQuery = "";
        if(searchType.equals("medName") ){
            searchQuery = " WHERE med_name = '"+words+"'";
        }else if(searchType.equals("medDis")){
            searchQuery = " WHERE med_dis LIKE '%"+words+"%'";
        }else if(searchType.equals("medEff")){
            searchQuery = " WHERE med_eff LIKE '%"+words+"%'";
        }else if(searchType.equals("medType")){
            searchQuery = " WHERE med_type = '"+words+"'";
        }else if(searchType.equals("medStore")){
            searchQuery = " WHERE med_store = '"+words+"'";
        }else if(searchType.equals("medCom")){
            searchQuery = " WHERE med_com = '"+words+"'";
        }else if(searchType.equals("medPregnant")){
            searchQuery = " WHERE med_pregnant = '"+words+"'";
        }else {
            searchQuery = "";
        }
        return searchQuery;
    }
    public void deleteMed(MedicineDto medicineDto){
        medicineMapper.deleteMed(medicineDto);
    }
    public List<FileDto> getFiles(int id){
        return medicineMapper.getFiles(id);
    }
    public void setFileDelete(int id){
        System.out.println(id);
        medicineMapper.setFileDelete(id);
    }
    public MedicineDto getMedView(int medId){
        return medicineMapper.getMedView(medId);
    }
    public MedicineDto getFileView(int medId){
        return medicineMapper.getFileView(medId);
    }


    //1226 jang
    public List<FileDto> getFilesAll(){
        return medicineMapper.getFilesAll();
    }
}
