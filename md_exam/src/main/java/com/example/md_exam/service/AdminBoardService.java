package com.example.md_exam.service;

import com.example.md_exam.dto.AdminBoardDto;
import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.PageDto;
import com.example.md_exam.mapper.AdminBoardMapper;
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
    public PageDto PageInfo(int page, String board) {
        PageDto pageDto = new PageDto();
        //전체 게시글 수
        int totalCount = adminBoardMapper.getBoardCount(board);
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
    public List<AdminBoardDto> getBoardList(int page, String board){
        PageDto pd = PageInfo(page,board);
        Map<String, Object> map = new HashMap<>();
        map.put("startNum",pd.getStartNum());
        map.put("offset",pd.getPageCount());
        map.put("board", board);
        return adminBoardMapper.getBoardList(map);
    }

}
