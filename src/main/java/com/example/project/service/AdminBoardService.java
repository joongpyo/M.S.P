package com.example.project.service;

import com.example.project.dto.AdminBoardDto;
import com.example.project.dto.FileDto;
import com.example.project.dto.MedicineDto;
import com.example.project.dto.PageDto;
import com.example.project.mapper.AdminBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminBoardService {
    @Autowired
    AdminBoardMapper adminBoardMapper;
    public int getGrpMaxCnt(String configCode){
        return adminBoardMapper.getGrpMaxCnt(configCode);
    }
    public void setAdminBoard(AdminBoardDto adminBoardDto){
        adminBoardMapper.setAdminBoard(adminBoardDto);
    }
    public void setFile(FileDto fileDto){
        adminBoardMapper.setFile(fileDto);
    }

    // Notice List
    public PageDto PageBoardInfo(int page, String configCode, String searchType, String words) {
        PageDto pageDto = new PageDto();
        String searchQuery = getBoardSearch(searchType,words);
        //전체 게시글 수
        int totalCount = adminBoardMapper.getBoardCount(configCode,searchQuery);
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
    public String getBoardSearch(String searchType, String words){
        String searchQuery = "";
        if(searchType.equals("subject") ){
            searchQuery = " WHERE subject = '"+words+"'";
        }else if(searchType.equals("writer")){
            searchQuery = " WHERE writer = '"+words+"'";
        }else if(searchType.equals("content")){
            searchQuery = " WHERE content LIKE '%"+words+"%'";
        }else {
            searchQuery = "";
        }
        return searchQuery;
    }

    public List<AdminBoardDto> getBoardList(int page, String configCode,String searchType,String words){
        PageDto pd = PageBoardInfo(page,configCode,searchType,words);
        Map<String, Object> map = new HashMap<>();
        String searchQuery = getBoardSearch(searchType,words);
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("configCode",configCode);
        map.put("searchQuery",searchQuery);
        return adminBoardMapper.getBoardList(map);
    }

    // Review List

}