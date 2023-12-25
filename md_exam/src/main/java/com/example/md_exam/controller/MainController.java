package com.example.md_exam.controller;

import com.example.md_exam.dto.BoardDto;
import com.example.md_exam.mapper.BoardMapper;
import com.example.md_exam.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    BoardService boardService;
    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("Notice",boardService.getBoard("Notice",1,"",""));
        model.addAttribute("noticeCode","Notice");
        model.addAttribute("QnA",boardService.getBoard("QnA",1,"",""));
        model.addAttribute("qnaCode","QnA");
        return "index";
    }


}
