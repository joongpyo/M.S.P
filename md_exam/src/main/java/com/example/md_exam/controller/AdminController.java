package com.example.md_exam.controller;

import com.example.md_exam.dto.AdminBoardDto;
import com.example.md_exam.dto.DiseaseDto;
import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.MedicineDto;
import com.example.md_exam.service.AdminBoardService;
import com.example.md_exam.service.DiseaseService;
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

    @Autowired
    DiseaseService diseaseService;

    @Autowired
    AdminBoardService adminBoardService;

    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/admin") //
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {
        model.addAttribute("current", current);
        return "admin";
    }
    @GetMapping("/admin/disInsert") // admin Disease insert window load
    public String getDisUpdate(){
        return "/admin/disInsert";
    }
    @GetMapping("/admin/checkDisName") // admin Disease insert check disease Name <> DB
    @ResponseBody
    public Map<String, Object> getCheckDisName(@RequestParam String disName){
        int checkDisName = diseaseService.getCheckDisName(disName);
        return Map.of("checkName",disName);
    }

    @PostMapping("/admin/disInsert")  // admin Disease insert
    @ResponseBody
    public Map<String, Object> setDisUpdate(@ModelAttribute DiseaseDto diseaseDto, @RequestParam String disName ){
        if(diseaseService.getCheckDisName(disName) < 1 ){
            diseaseService.setDisease(diseaseDto);
            return Map.of("msg","success");
        }else{
            return Map.of("msg","failure");
        }
    }

    @GetMapping("admin/noticeInsert")
    public String getNoticeUpdate(){
        return "admin/noticeInsert";
    }
    @PostMapping("/admin/noticeInsert")
    @ResponseBody
    public Map<String,Object> setNoticeInsert(@ModelAttribute AdminBoardDto adminBoardDto, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(adminBoardDto);
        if (!file.isEmpty()) {
            adminBoardDto.setIsFiles("Y");
            adminBoardService.setAdminBoard(adminBoardDto);
            int fileId = adminBoardDto.getId();
            String board = adminBoardDto.getBoard();
            System.out.println("jjj"+fileId);

            //20231218
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);
            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }
            // 데이터 베이스 저장전에 잠시 저장
            FileDto fileDto = new FileDto();

            // 경로명  + UUID
            String savedPathFileName = fileDir + folderName;
            String orgName = file.getOriginalFilename(); //원본 이름 저장
            String ext = orgName.substring(orgName.lastIndexOf(".")); // 확장자명 가져오기
            String uuid = UUID.randomUUID().toString(); // 랜덤으로 만드는 임의의 난수 이름
            String savedFileName = uuid + ext; // 랜덤 이름  + 확장자로 저장하는 이름

            file.transferTo(new File(savedPathFileName + "/" + savedFileName));

            fileDto.setBoard(board);
            fileDto.setId(fileId);
            fileDto.setOrgName(orgName);
            fileDto.setSavedFileName(savedFileName);
            fileDto.setSavedPathFileName(savedPathFileName);
            fileDto.setFolderName(folderName);
            fileDto.setExt(ext);

            System.out.println(fileDto);
            adminBoardService.setFile(fileDto);
            System.out.println(board);

            System.out.println(fileDto);
            System.out.println(adminBoardDto);
            return null;
//            return Map.of("msg", "success");
        } else if (file.isEmpty()) {
            adminBoardDto.setIsFiles("N");
            adminBoardService.setAdminBoard(adminBoardDto);
            return Map.of("msg", "success");
        }else {
            return Map.of("msg","failure");

        }

    }

    @GetMapping("admin/userInsert")
    public String getUserUpdate(){
        return "admin/userInsert";
    }
    @GetMapping("/admin/medInsert")
    public String getMedUpdate(){
        return "/admin/medInsert";

    }
    @PostMapping("/admin/medInsert")
    @ResponseBody
    public Map<String, Object> setMedUpdate(@ModelAttribute MedicineDto medicineDto, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(medicineDto);
        if (!file.isEmpty()) {
            medicineDto.setIsFiles("Y");
            medicineService.setMedUpdate(medicineDto);
            int fileId = medicineDto.getMedId();
            //20231218
            String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
            File makeFolder = new File(fileDir + folderName);
            if (!makeFolder.exists()) {
                makeFolder.mkdir();
            }
            // 데이터 베이스 저장전에 잠시 저장
            FileDto fileDto = new FileDto();

            // 경로명  + UUID
            String savedPathFileName = fileDir + folderName;
            String orgName = file.getOriginalFilename(); //원본 이름 저장
            String ext = orgName.substring(orgName.lastIndexOf(".")); // 확장자명 가져오기
            String uuid = UUID.randomUUID().toString(); // 랜덤으로 만드는 임의의 난수 이름
            String savedFileName = uuid + ext; // 랜덤 이름  + 확장자로 저장하는 이름

            file.transferTo(new File(savedPathFileName + "/" + savedFileName));

            fileDto.setId(fileId);
            fileDto.setOrgName(orgName);
            fileDto.setSavedFileName(savedFileName);
            fileDto.setSavedPathFileName(savedPathFileName);
            fileDto.setFolderName(folderName);
            fileDto.setExt(ext);

            medicineService.setFile(fileDto);
            System.out.println(fileDto);
            System.out.println(medicineDto);
            return Map.of("msg", "success");
        } else {
            return Map.of("msg", "failure");
        }


    }
}
