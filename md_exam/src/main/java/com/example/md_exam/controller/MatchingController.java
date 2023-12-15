package com.example.md_exam.controller;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.UserDto;
import com.example.md_exam.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/matching")
public class MatchingController {

    @Autowired
    MatchingService matchingService;

    @GetMapping("/matching")
    public String getMatching() {
        return "matching/matching";
    }

    @GetMapping("/match_result")
    public String getMatchResult() {
        return "matching/match_result";
    }

    @PostMapping("/matching")
    public String setMatching(@ModelAttribute MedicineDto medicineDto, RedirectAttributes ra, HttpServletRequest hsr){
        matchingService.setMatching(medicineDto);


        return "redirect:/matching/Match_result";
    }

}