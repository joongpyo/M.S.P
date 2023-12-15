package com.example.md_exam.service;

import com.example.md_exam.dto.QnADto;
import com.example.md_exam.mapper.BoardQnAMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardQnAService {
    @Autowired
    BoardQnAMapper boardQnAMapper;

    public void setBoard(QnADto qnADto){
        boardQnAMapper.setBoard(qnADto);
    }
}
