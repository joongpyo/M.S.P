package com.example.md_exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {
        model.addAttribute("current", current);
        return "/admin";
    }
    @PostMapping("/admin/medUpdate")
    public String setMedUpdate(){
        return null;
    }

}
