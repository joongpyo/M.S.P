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

    @GetMapping("/match_result") //Requestparam은 단일"변수" Modelatt.는 객체, Model 서버에서 클라이언트
    public String getMatchResult(@ModelAttribute MedicineDto medicineDto, Model model) {
        System.out.println(medicineDto);
        model.addAttribute("medicine", medicineDto);
        return "matching/match_result";
    }

    @PostMapping("/matching")
    @ResponseBody
    public Map<String, Object> setMatching(@ModelAttribute MedicineDto medicineDto) {
        MedicineDto md =  matchingService.setMatching(medicineDto);

        return Map.of("medicine", md);
    }




}