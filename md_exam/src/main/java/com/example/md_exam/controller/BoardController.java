package com.example.md_exam.controller;

import com.example.md_exam.dto.QnaDto;
import com.example.md_exam.service.BoardQnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
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
    public String getBoardView() {
        return "board/boardView";
    }

    @GetMapping("/boardWrite")
    public String getBoardWrite(){
        return "board/boardWrite";
    }

    @PostMapping("/boardWrite")
    @ResponseBody
    public Map<String, Object> setBoardWrite(@RequestParam(name="files",required = false)List<MultipartFile> files,
                                             @ModelAttribute QnaDto qnaDto) {

        //파일 저장
        String folderName = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());

        if (files != null){
            for(MultipartFile mf : files){
                System.out.println(mf.getOriginalFilename());
                qnaDto.setIsFiles("Y");
                boardQnaService.setBoard(qnaDto);

               /* int fileID = qnADto.getId();
                String savedPathName = fileDir + folderName;

                String orgName = mf.getOriginalFilename();
                String ext = orgName.substring(orgName.lastIndexOf("."));
                String uuid = UUID.randomUUID().toString();
                String savedFileName = uuid + ext;

                //mf.transferTo(new File(savedPathName + "/" + savedFileName));

                fileDto.setId(fileID);
                fileDto.setOrgName(orgName);
                fileDto.setSavedFileName(savedFileName);
                fileDto.setSavedPathName(savedPathName);
                fileDto.setFolderName(folderName);
                fileDto.setExt(ext);

                boardService.setFiles(fileDto);*/
            }
        }else {
            qnaDto.setIsFiles("N");
            boardQnaService.setBoard(qnaDto);
        }

        System.out.println(qnaDto);
        return Map.of("msg","success");
    }

    @GetMapping("/board/qnaList")
    public void getQnaList(){

        List<QnaDto> list = boardQnaService.getQnaList();


    }

}
