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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String getLogin(HttpServletRequest hsr){
        String referer = "";

        String refererHeader = hsr.getHeader("Referer");
        final String LOGIN_REFERER = "http://localhost:7777/user/login";
        final String REGISTER_REFERER = "http://localhost:7777/user/register";
        final String FIND_USER_REFERER = "http://localhost:7777/findUser";
        final String INDEX_PAGE = "http://localhost:7777/index";

        if (refererHeader != null) {
            refererHeader = refererHeader.toLowerCase();
            if (refererHeader.startsWith(LOGIN_REFERER.toLowerCase()) ||
                    refererHeader.startsWith(REGISTER_REFERER.toLowerCase()) ||
                    refererHeader.startsWith(FIND_USER_REFERER.toLowerCase())) {

                referer = INDEX_PAGE;
            } else {
                referer = hsr.getHeader("Referer");
            }
        }

        hsr.getSession().setAttribute("prevPage",referer);
        return "user/login";
    }

    @GetMapping("/user/register")
    public String getRegister(){
        return "user/register";
    }
    @GetMapping("/user/checkUserIdAndEmail")
    @ResponseBody
    public Map<String, Object> checkUserIdAndEmail(@RequestParam String userId,@RequestParam String userEmail) {
        int checkUserId = userService.getCheckUserId(userId);
        int checkUserEmail = userService.getCheckUserEmail(userEmail);
        return Map.of("checkUserId",checkUserId,"checkUserEmail",checkUserEmail);
    }
    @PostMapping("/user/register")
    @ResponseBody
    public Map<String,Object> setRegister(@ModelAttribute UserDto userDto,@RequestParam String userId,@RequestParam String userEmail ){
        if( userService.getCheckUserId(userId) < 1 && userService.getCheckUserEmail(userEmail) < 1  ){
            userService.setRegister(userDto);
            return Map.of("msg","success");
        }else{
            return Map.of("msg","failure");
        }
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

        hs.invalidate();
        return "redirect:/index";
    }

    @GetMapping("/checkLogin")
    @ResponseBody
    public Map<String,Object> checkLogin(HttpSession hs){
        boolean isLogin = hs.getAttribute("user") != null;

        return Map.of("isLogin",isLogin);
    }

    @GetMapping("/findUser")
    public String getFindUser(){
        return "/user/findUser";
    }

    @PostMapping("/findUser")
    @ResponseBody
    public Map<String,Object> getUser(@ModelAttribute UserDto userDto,@RequestParam String type) {
        Map<String,Object> map = new HashMap<>();

        if ( userService.getFindUser(userDto,type) == null){
            map.put("type","");
        }
        else{
            map.put("type", type);
            map.put("msg", "success");
            map.put("user", userService.getFindUser(userDto, type));
        }
        return map;
    }

}
