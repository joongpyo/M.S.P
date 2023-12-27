package com.example.project.controller;

import com.example.project.dto.*;
import com.example.project.mapper.AdminBoardMapper;
import com.example.project.mapper.DiseaseMapper;
import com.example.project.mapper.MedicineMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.service.AdminBoardService;
import com.example.project.service.DiseaseService;
import com.example.project.service.MedicineService;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
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
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    MedicineMapper medicineMapper;
    @Autowired
    AdminBoardMapper adminBoardMapper;
    @Autowired
    DiseaseMapper diseaseMapper;


    @Value("${fileDir}")
    String fileDir;

    @GetMapping("/admin") //
    public String getAdmin(@RequestParam(value="current", defaultValue="1") String current, Model model) {
        model.addAttribute("current", current);
        return "admin";
    }
    @GetMapping("/admin/userList")
    public String getUserList(Model model, @RequestParam(value="page", defaultValue = "1") int page,@RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words ){
        model.addAttribute("user",userService.getUserList(page,searchType,words));
        model.addAttribute("page",userService.PageInfo(page,searchType,words));
        String searchQuery = userService.getSearch(searchType,words);
        model.addAttribute("total",userMapper.getUserCount(searchQuery));
        return "admin/userPage/userList";
    }

    @GetMapping("/admin/userDelete")
    public String getUserDelete(@ModelAttribute UserDto userDto){
        return null;
    }

    @GetMapping("/admin/disList") // 질병 리스트  -> 리스트 안에서 삭제 버튼 만들어서 사용 메인페이지 안에 작성페이지로 이동하는 버튼 만들기
    public String getDisList(Model model, @RequestParam(value = "page", defaultValue = "1")int page,@RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words){

        model.addAttribute("dis",diseaseService.getDisList(page,searchType,words));
        model.addAttribute("page",diseaseService.PageInfo(page,searchType,words));
        String searchQuery = diseaseService.getDisSearch(searchType,words);
        model.addAttribute("total",diseaseMapper.getDisCount(searchQuery));

        return "admin/diseasePage/disList";
    }
    @GetMapping("/admin/disInsert") // admin Disease insert window load
    public String getDisUpdate(){
        return "/admin/diseasePage/disInsert";
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
    @GetMapping("/admin/disDelete")
    public String disDelete(@ModelAttribute DiseaseDto diseaseDto){
        diseaseService.deleteDis(diseaseDto);
        return "redirect:/admin/disList?disId="+ diseaseDto.getDisId();
    }
    @GetMapping("/admin/noticeMain")
    public String getNoticeList(){
        return "admin/noticePage/noticeMain";
    }
    @GetMapping("/admin/noticeBoard")
    public String getNoticeBoard(Model model,@RequestParam(value = "page", defaultValue = "1")int page,@RequestParam String configCode){
        System.out.println(configCode);
        model.addAttribute("configCode",configCode);
        model.addAttribute("notice",adminBoardService.getBoardList(page,configCode));
        model.addAttribute("page",adminBoardService.PageBoardInfo(page,configCode));
        model.addAttribute("total",adminBoardMapper.getBoardCount(configCode));
        return "admin/noticePage/noticeBoard";

    }
    @GetMapping("/admin/qnaBoard")
    public String getQnaBoard(Model model, @RequestParam(value = "page", defaultValue = "1")int page, @RequestParam String configCode){
        System.out.println(configCode);
        model.addAttribute("configCode",configCode);
        model.addAttribute("qna",adminBoardService.getBoardList(page,configCode));
        model.addAttribute("page",adminBoardService.PageBoardInfo(page,configCode));
        model.addAttribute("total",adminBoardMapper.getBoardCount(configCode));
        return "admin/noticePage/qnaBoard";
    }
    @GetMapping("/admin/reviewBoard")
    public String getReviewBoard(Model model,@RequestParam(value = "page", defaultValue = "1")int page, @RequestParam String configCode){
        System.out.println(configCode);
        model.addAttribute("configCode",configCode);
        model.addAttribute("review",adminBoardService.getBoardList(page,configCode));
        model.addAttribute("page",adminBoardService.PageBoardInfo(page,configCode));
        model.addAttribute("total",adminBoardMapper.getBoardCount(configCode));
        return "admin/noticePage/reviewBoard";
    }
    @GetMapping("admin/noticeInsert")
    public String getNoticeUpdate(){
        return "admin/noticePage/noticeInsert";
    }
    @PostMapping("/admin/noticeInsert")
    @ResponseBody
    public Map<String,Object> setNoticeInsert(@ModelAttribute AdminBoardDto adminBoardDto, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(adminBoardDto);
        if(adminBoardDto.getConfigCode().equals("review") && !file.isEmpty()){
            return Map.of("msg","choice1");
        }else {
            if (!file.isEmpty()) {
                adminBoardDto.setIsFiles("Y");
                adminBoardService.setAdminBoard(adminBoardDto);
                int fileId = adminBoardDto.getId();
                String configCode = adminBoardDto.getConfigCode();
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

                fileDto.setConfigCode(configCode);
                fileDto.setId(fileId);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathFileName);
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);
                adminBoardService.setFile(fileDto);
                return Map.of("msg","success");
            }else if(file.isEmpty()) {
                adminBoardDto.setIsFiles("N");
                adminBoardService.setAdminBoard(adminBoardDto);
                return Map.of("msg","success");
            }else {
                return Map.of("msg","failure");
            }

        }

    }
    @GetMapping("/admin/medList")
    public String getMedList(Model model, @RequestParam(value = "page", defaultValue = "1")int page,@RequestParam(value = "searchType", defaultValue = "") String searchType, @RequestParam(value = "words", defaultValue = "") String words){
        model.addAttribute("med",medicineService.getMedList(page,searchType,words));
        model.addAttribute("page",medicineService.PageInfo(page,searchType,words));
        String searchQuery = medicineService.getMedSearch(searchType,words);
        model.addAttribute("total",medicineMapper.getMedCount(searchQuery));
        return "/admin/medicinePage/medList";
    }
    @GetMapping("/admin/medInsert")
    public String getMedUpdate(){
        return "/admin/medicinePage/medInsert";
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
            fileDto.setSavedPathName(savedPathFileName);
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
    @GetMapping("/admin/medDelete")
    public String medDelete(@ModelAttribute MedicineDto medicineDto){
        if( medicineDto.getMedId() > 0 && !medicineDto.getMedName().isEmpty() ){
            medicineService.deleteMed(medicineDto);
        }
        List<FileDto> files = medicineService.getFiles(medicineDto.getMedId());
        for(FileDto fd : files){
            File file = new File(fd.getSavedPathName()+"/"+fd.getSavedFileName());
            file.delete();
        }
        // 파일 DB 삭제
        int id = medicineDto.getMedId();
        medicineService.setFileDelete(id);
        return "redirect:/admin/medList?medId="+ medicineDto.getMedId();
    }
    @GetMapping("/admin/medUpdate")
    public String boardView(@RequestParam int medId, Model model){

        model.addAttribute("med",medicineService.getMedView(medId));
        model.addAttribute("file",medicineService.getFileView(medId));

        return "admin/medUpdate";

    }
}
