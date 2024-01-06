package com.example.project.controller;

import com.example.project.dto.MyMedicineDto;
import com.example.project.dto.UserDto;
import com.example.project.service.MypageService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    UserService userService;

    @Autowired
    MypageService mypageService;



    @GetMapping("")
    public String getMypage(){
        return "mypage";
    }

    @GetMapping("/update")
    public String getUpdate(){
        return "mypage/update";
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> setUpdate(@ModelAttribute UserDto userDto, HttpSession session){
        userService.updateUser(userDto);
        // 비밀번호 업데이트 - 예시: 사용자 정보가 User 객체로 저장되어 있다고 가정
        UserDto uDto = (UserDto) session.getAttribute("user");
        uDto.setUserPasswd(userDto.getUserPasswd());

        // 세션 업데이트 (생략 가능)
        session.setAttribute("user", uDto);
        return Map.of("msg", "success");
    }

    @GetMapping("/myMedList")
    public String getMyMedList(){

        return "mypage/myMedList";
    }



    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> setMyMedList(@ModelAttribute MyMedicineDto myMedicineDto){
        mypageService.insertMyMed(myMedicineDto);
        return Map.of("msg", "success");
    }

    @GetMapping("/myBoard")
    public String getMyBoard(){
        return "mypage/myBoard";
    }

    @GetMapping("/myQuit")
    public String getMyQuit(){
        return "mypage/myQuit";
    }

    @PostMapping("/myQuit")
    @ResponseBody
    public Map<String, Object> deleteMyQuit(@ModelAttribute UserDto userDto){
        userService.deleteUser(userDto);
        return Map.of("msg", "success");
    }







}
