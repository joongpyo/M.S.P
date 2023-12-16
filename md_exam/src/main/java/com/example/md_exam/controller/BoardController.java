package com.example.md_exam.controller;

import com.example.md_exam.dto.FileDto;
import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.service.BoardQnaService;
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
import java.util.UUID;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Value("${fileDir}")
    String fileDir;

    @Autowired
    BoardQnaService boardQnaService;

    @GetMapping("/boardNotice")
    public String getBoardNotice(){
        return "board/boardNotice";
    }

    @GetMapping("/boardQnA")
    public String getBoardQnA(){
        return "board/boardQnA";
    }



    @GetMapping("/boardList")
    public String getBoardList(){
        return "board/boardList";
    }

    @GetMapping("/boardReview")
    public String getBoardReview(){
        return "board/boardReview";
    }

    @GetMapping("/boardView")
    public String getBoardView(@RequestParam int qnaId, Model model) {
        model.addAttribute("board",boardQnaService.getQnaView(qnaId));
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    @ResponseBody
    public Map<String, Object> setBoardWrite(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                             @ModelAttribute QnaDto qnaDto) throws IOException {

        //파일 저장
        String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

        File makeFolder = new File(fileDir + folderName);
        if(!makeFolder.exists()){
            makeFolder.mkdir();
        }

        if (files != null){
            qnaDto.setIsFiles("Y");

            boardQnaService.setBoard(qnaDto);
            System.out.println(qnaDto);

            //자동으로 증가되는 id값을 얻기 위해 mapper에서 @Options(useGeneratedKeys = true, keyProperty = "qnaId") 사용
            int fileID = qnaDto.getQnaId();

            for(MultipartFile mf : files){
                FileDto fileDto= new FileDto();

                String savedPathName = fileDir + folderName;
                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;
                Long savedFileSize = mf.getSize();

                mf.transferTo(new File(savedPathName + "/" + savedFileName));

                fileDto.setId(fileID);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathName);
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);
                fileDto.setSavedFileSize(savedFileSize);

                boardQnaService.setFiles(fileDto);
            }
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setBoard(qnaDto);
        }
        return Map.of("msg","success");
    }

    @GetMapping("/qnaList")
    @ResponseBody
    public Map<String,Object> getQnaList(){
        List<QnaDto> list = boardQnaService.getQnaList();
        return Map.of("qnaList",list);
    }

}
