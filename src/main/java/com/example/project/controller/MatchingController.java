package com.example.project.controller;

import com.example.project.dto.MedicineDto;
import com.example.project.dto.UserDto;
import com.example.project.service.MatchingService;
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



    @PostMapping("/matching")
    @ResponseBody
    public Map<String, Object> setMatching(@ModelAttribute MedicineDto medicineDto, HttpSession session) {
        List<MedicineDto> searchResults =  matchingService.setMatching(medicineDto);
        session.setAttribute("searchResults", searchResults);
        System.out.println("검색 : " + searchResults);

        return Map.of("msg", "success");
    }

    @GetMapping("/match_result")
    public String getMatchResult(HttpSession session, Model model) {
        List<MedicineDto> searchResults = (List<MedicineDto>) session.getAttribute("searchResults");

        model.addAttribute("searchResults",searchResults);
        System.out.println("결과 : " +searchResults);

        return "matching/match_result";
    }



}