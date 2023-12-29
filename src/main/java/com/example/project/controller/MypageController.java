package com.example.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Mypage")
public class MypageController {

    @GetMapping("/update")
    public String getHome(){

        return "Mypage/update";
    }

}
