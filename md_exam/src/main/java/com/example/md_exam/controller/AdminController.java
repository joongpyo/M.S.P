package com.example.md_exam.controller;

import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.dto.MedicineFileDto;
import com.example.md_exam.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


@Controller
public class AdminController {
    @Autowired
    MedicineService medicineService;


    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/admin")
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {
        model.addAttribute("current", current);
        return "admin";
    }
    @GetMapping("/admin/disUpdate")
    public String getDisUpdate(){
        return "/admin/disUpdate";
    }
    @GetMapping("admin/noticeUpdate")
    public String getNoticeUpdate(){
        return "admin/noticeUpdate";
    }
    @GetMapping("admin/userUpdate")
    public String getUserUpdate(){
        return "admin/UserUpdate";
    }
    @GetMapping("/admin/medUpdate")
    public String getMedUpdate(){
        return "/admin/medUpdate";

    }
    @PostMapping("/admin/medUpdate")
    @ResponseBody
    public Map<String, Object> setMedUpdate(@ModelAttribute MedicineDto medicineDto, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(medicineDto);
        if (!file.isEmpty()) {
            medicineDto.setMedIsFiles("Y");
            medicineService.setMedUpdate(medicineDto);
            int fileId = medicineDto.getMedId();
            //20231218
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);
            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }
            // 데이터 베이스 저장전에 잠시 저장
            MedicineFileDto medicineFileDto = new MedicineFileDto();

            // 경로명  + UUID
            String savedPathFileName = fileDir + folderName;
            String orgName = file.getOriginalFilename(); //원본 이름 저장
            String ext = orgName.substring(orgName.lastIndexOf(".")); // 확장자명 가져오기
            String uuid = UUID.randomUUID().toString(); // 랜덤으로 만드는 임의의 난수 이름
            String savedFileName = uuid + ext; // 랜덤 이름  + 확장자로 저장하는 이름

            file.transferTo(new File(savedPathFileName + "/" + savedFileName));

            medicineFileDto.setId(fileId);
            medicineFileDto.setOrgName(orgName);
            medicineFileDto.setSavedFileName(savedFileName);
            medicineFileDto.setSavedPathFileName(savedPathFileName);
            medicineFileDto.setFolderName(folderName);
            medicineFileDto.setExt(ext);

            medicineService.setFile(medicineFileDto);
            System.out.println(medicineFileDto);
            System.out.println(medicineDto);
            return Map.of("msg", "success");
        } else {
            return Map.of("msg", "failure");
        }


    }
}
