package com.example.md_exam.service;

import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.mapper.BoardQnaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardQnaService {
    @Autowired
    BoardQnaMapper boardQnaMapper;

    public void setBoard(QnaDto qnADto){
        boardQnaMapper.setBoard(qnADto);
    }

    public List<QnaDto> getQnaList(){
        return boardQnaMapper.getQnaList();
    }
}
