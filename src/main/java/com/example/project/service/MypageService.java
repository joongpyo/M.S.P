package com.example.project.service;

import com.example.project.dto.BoardDto;
import com.example.project.dto.CommentDto;
import com.example.project.dto.MyMedicineDto;
import com.example.project.dto.UserDto;
import com.example.project.mapper.BoardMapper;
import com.example.project.mapper.CommentMapper;
import com.example.project.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MypageService {

    @Autowired
    MypageMapper mypageMapper;
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    CommentMapper commentMapper;

    public void insertMyMed(MyMedicineDto myMedicineDto){
        mypageMapper.insertMyMed(myMedicineDto);
    }

    public void getPost(UserDto siteUser){
        List<BoardDto> qBoardList = boardMapper.getBoardAll("qna", siteUser.getuId());
        List<BoardDto> rBoardList = boardMapper.getBoardAll("review", siteUser.getuId());

        for (BoardDto board : qBoardList) {
            board.setConfigCode("qna");
        }
        for (BoardDto board : rBoardList) {
            board.setConfigCode("review");
        }

        List<BoardDto> combineList = Stream.concat(qBoardList.stream(), rBoardList.stream())
                .sorted(Comparator.comparing(BoardDto::getReg))
                .collect(Collectors.toList());

        System.out.println(combineList);

    }


}
