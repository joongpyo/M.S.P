package com.example.md_exam.controller;

import com.example.md_exam.dto.BoradQnADto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/boardNotice")
    public String getBoardNotice(){
        return "board/boardNotice";
    }

    @GetMapping("/boardQnA")
    public String getBoardQnA(){
        return "board/boardQnA";
    }

    @GetMapping("/boardList")
    public String getBoardList(){
        return "board/boardList";
    }

    @GetMapping("/boardReview")
    public String getBoardReview(){
        return "board/boardReview";
    }
    @GetMapping("/boardView")
    public String getBoardView() {
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }

    @PostMapping("boardWrite")
    @ResponseBody
    public String setBoardWrite(@RequestParam List<MultipartFile> files,
                                @ModelAttribute BoradQnADto boradQnADto,
                                Model model) {
        System.out.println(boradQnADto);
        return null;
    }

}
