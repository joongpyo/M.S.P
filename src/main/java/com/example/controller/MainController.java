package com.example.controller;

import com.example.dto.BoardDto;
import com.example.mapper.BoardMapper;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    BoardService boardService;
    @GetMapping("/index")
    public String getIndex(Model model){
        List<BoardDto> noticeList = boardService.getBoard("Notice",1,"","");
        List<BoardDto> qnaList = boardService.getBoard("QnA",1,"","");

        model.addAttribute("Notice", noticeList.subList(0, Math.min(noticeList.size(), 5)));
        model.addAttribute("noticeCode","Notice");
        model.addAttribute("QnA",qnaList.subList(0, Math.min(qnaList.size(), 5)));
        model.addAttribute("qnaCode","QnA");
        return "index";
    }
}
