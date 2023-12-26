package com.example.project.controller;

import com.example.project.dto.UserDto;
import com.example.project.mapper.UserMapper;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String getLogin(HttpServletRequest hsr){
        String referer = "";

        if (hsr.getHeader("Referer").equals("http://localhost:7777/user/login") ||
                hsr.getHeader("Referer").equals("http://localhost:7777/user/register")){
            referer = "http://localhost:7777/index";
        }else {
            referer = hsr.getHeader("Referer");
        }

        hsr.getSession().setAttribute("prevPage",referer);
        return "user/login";
    }

    @GetMapping("/user/register")
    public String getRegister(){
        return "user/register";
    }
    @PostMapping("/user/register")
    public String setRegister(@ModelAttribute UserDto userDto, RedirectAttributes ra){
        userService.setRegister(userDto);

        ra.addFlashAttribute("msg" , "success");
        return "redirect:/user/login";
    }

    @PostMapping("/login")
    public String setLogin(@ModelAttribute UserDto userDto, RedirectAttributes ra,HttpSession session, HttpServletRequest hsr){

        UserDto d = userService.setLogin(userDto);



        String prevPage = (String) session.getAttribute("prevPage");


        if(d != null){
            //세션 생성 - 로그아웃하기전까지 계속 로그인유지
            //getSession() -> 데이터 -> 시간

            HttpSession hs = hsr.getSession(); //세션 준비
            hs.setAttribute("user",d);
            hs.setMaxInactiveInterval(15*60);   //10분
            ra.addFlashAttribute("userName",d.getUserName());

            if(d.getUserId().equals("admin")){
                return "redirect:/admin";
            }else if(prevPage != null){
                return "redirect:"+prevPage;
            }else {
                return "redirect:/index";
            }

        }else{
            ra.addFlashAttribute("message","아이디/비밀번호를 확인하세요");
            return "redirect:/user/login";
        }
    }
    @GetMapping("/logout")
    public String getLogout(HttpSession hs) {
        System.out.println("logout");
        hs.invalidate();
        return "redirect:/index";
    }

}
