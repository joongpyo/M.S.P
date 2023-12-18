package com.example.md_exam.controller;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.UserDto;
import com.example.md_exam.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @ResponseBody
    public String setMatching(@ModelAttribute MedicineDto medicineDto, Model model){
        System.out.println(medicineDto);
        MedicineDto md =  matchingService.setMatching(medicineDto);
        model.addAttribute("medicine", md);
        System.out.println("결과 : " + md);
        return null;
    }




}