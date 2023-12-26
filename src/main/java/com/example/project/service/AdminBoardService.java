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
    public void setAdminBoard(AdminBoardDto adminBoardDto){
        adminBoardMapper.setAdminBoard(adminBoardDto);
    }
    public void setFile(FileDto fileDto){
        adminBoardMapper.setFile(fileDto);
    }

    // Notice List
    public PageDto PageBoardInfo(int page, String configCode) {
        PageDto pageDto = new PageDto();
        //전체 게시글 수
        int totalCount = adminBoardMapper.getBoardCount(configCode);
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
    public List<AdminBoardDto> getBoardList(int page, String configCode){
        PageDto pd = PageBoardInfo(page,configCode);
        Map<String, Object> map = new HashMap<>();
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("configCode",configCode);
        return adminBoardMapper.getBoardList(map);
    }

    // Review List

}