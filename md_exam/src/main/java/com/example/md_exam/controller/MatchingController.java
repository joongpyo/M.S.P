package com.example.md_exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matching")
public class MatchingController {

    @GetMapping("/search")
    public String getSearch(){
        return "matching/search";
    }

    @GetMapping("/match_result")
    public String getMatchResult(){
        return "matching/match_result";
    }

}
