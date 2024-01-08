package com.example.project.service;

import com.example.project.dto.*;
import com.example.project.mapper.BoardMapper;
import com.example.project.mapper.CommentMapper;
import com.example.project.mapper.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String,Object> getPost(UserDto siteUser, int page){
        PageDto pageDto = new PageDto();

        List<BoardDto> qBoardList = boardMapper.getBoardAll("qna", siteUser.getuId());
        List<BoardDto> rBoardList = boardMapper.getBoardAll("review", siteUser.getuId());


        int pageSize = 5; // 한 페이지에 표시할 항목 수

        for (BoardDto board : qBoardList) {
            board.setConfigCode("qna");
        }
        for (BoardDto board : rBoardList) {
            board.setConfigCode("review");
        }
        List<BoardDto> combineList = Stream.concat(qBoardList.stream(), rBoardList.stream())
                .sorted(Comparator.comparing(BoardDto::getReg))
                .collect(Collectors.toList());

        int totalCount = combineList.size();
        pageDto.setPageCount(pageSize);

        int totalPage = (int) Math.ceil((double) totalCount / pageDto.getPageCount());
        int startPage =  ((int) (Math.ceil((double) page / pageDto.getBlockCount())) - 1) * pageDto.getBlockCount() + 1;
        int endPage = startPage + pageDto.getBlockCount() - 1;

        if( endPage > totalPage ) {
            endPage = totalPage;
        }

        pageDto.setStartNum( (page - 1) * pageDto.getPageCount()  ); //startNum
        pageDto.setTotalPage(totalPage);
        pageDto.setStartPage(startPage);
        pageDto.setEndPage(endPage);
        pageDto.setPage(page);

        int start = (page - 1) * pageSize; // 각 페이지의 시작 인덱스
        int end = Math.min(start + pageSize, combineList.size());

        List<BoardDto> myBoardPage = combineList.subList(start,end);
        int total = combineList.size();

        Map<String,Object> map = new HashMap<>();
        map.put("myBoardPage",myBoardPage);
        map.put("page", pageDto);
        map.put("total",total);

        return map;
    }
}
