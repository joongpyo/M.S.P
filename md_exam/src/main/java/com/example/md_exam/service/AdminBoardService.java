package com.example.md_exam.service;

import com.example.md_exam.dto.AdminBoardDto;
import com.example.md_exam.dto.FileDto;
import com.example.md_exam.mapper.AdminBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
