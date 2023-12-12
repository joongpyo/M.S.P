package com.example.md_exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getBoardView(){
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }
}
